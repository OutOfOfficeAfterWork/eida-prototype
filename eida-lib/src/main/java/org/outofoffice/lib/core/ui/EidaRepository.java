package org.outofoffice.lib.core.ui;

import org.outofoffice.lib.context.EidaContext;
import org.outofoffice.lib.core.client.EidaDllClient;
import org.outofoffice.lib.core.client.EidaDmlClient;
import org.outofoffice.lib.exception.EidaException;
import org.outofoffice.lib.util.ClassUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;
import static org.outofoffice.lib.util.ClassUtils.getterName;
import static org.outofoffice.lib.util.ClassUtils.setterName;


public abstract class EidaRepository<T extends EidaEntity<ID>, ID> {

    private EidaDllClient managerClient;
    private EidaDmlClient shardClient;
    private EidaSerializer serializer;


    public void init(EidaDllClient managerClient, EidaDmlClient shardClient, EidaSerializer serializer) {
        this.managerClient = managerClient;
        this.shardClient = shardClient;
        this.serializer = serializer;
    }


    private Class<? extends EidaRepository<T, ID>> repositoryClass() {
        return (Class<? extends EidaRepository<T, ID>>) getClass();
    }

    private Class<T> entityClass() {
        return ClassUtils.toEntityClass(repositoryClass());
    }

    private String tableName() {
        return ClassUtils.toTableName(entityClass());
    }


    public void insert(T entity) {
        String serialized = serializer.serialize(entity);
        String destinationShardUrl = managerClient.getDestinationShardUrl(tableName());
        shardClient.insert(destinationShardUrl, tableName(), serialized);
        managerClient.postShardUrl(destinationShardUrl, tableName(), entity.getId());
    }

    public void update(T entity) {
        String serialized = serializer.serialize(entity);
        String sourceShardUrl = managerClient.getSourceShardUrl(tableName(), entity.getId());
        shardClient.update(sourceShardUrl, tableName(), serialized);
    }

    public void delete(ID id) {
        String sourceShardUrl = managerClient.getSourceShardUrl(tableName(), id.toString());
        shardClient.delete(sourceShardUrl, tableName(), id);
        managerClient.deleteShardUrl(sourceShardUrl, tableName(), id);
    }

    public void deleteAll() {
        listAll().forEach(entity -> delete(entity.getId()));
    }

    public Optional<T> find(ID id) {
        String sourceShardUrl = managerClient.getSourceShardUrl(tableName(), id);
        String tableString = shardClient.selectById(sourceShardUrl, tableName(), id);
        List<T> entities = serializer.deserialize(tableString, entityClass());
        if (entities.size() > 1) throw new EidaException("return value size > 1");
        return Optional.ofNullable(!entities.isEmpty() ? entities.get(0) : null);
    }

    public List<T> listAll() {
        List<String> allShardUrls = managerClient.getAllShardUrls(tableName());

        String header = null;
        StringBuilder packedTableBuilder = new StringBuilder();
        for (String shardUrl : allShardUrls) {
            String tableString = shardClient.selectAll(shardUrl, tableName());
            String[] headerAndBody = tableString.split("\n", 2);

            String firstLine = headerAndBody[0];
            if (header == null) {
                header = firstLine;
                packedTableBuilder.append(header).append("\n");
            }

            String bodyString = headerAndBody[1];
            packedTableBuilder.append(bodyString).append("\n");
        }
        String packedTableString = packedTableBuilder.toString();

        return serializer.deserialize(packedTableString, entityClass());
    }

    public Optional<T> joinFind(ID id, String fieldName) {
        return find(id).map(entity -> join(entity, fieldName));
    }

    private <J extends EidaEntity<FK>, FK> T join(T entity, String fieldName) {
        Class<? extends EidaEntity> entityClass = entity.getClass();

        try {
            J emptyTarget = (J) entityClass.getDeclaredMethod(getterName(fieldName)).invoke(entity);
            FK targetId = emptyTarget.getId();

            Class<J> joinTargetClass = (Class<J>) entityClass.getDeclaredField(fieldName).getType();
            EidaRepository<J, FK> joinTargetRepository = (EidaRepository<J, FK>) EidaContext.getRepository(joinTargetClass);

            J foundTarget = joinTargetRepository.find(targetId).orElse(null);
            entityClass.getDeclaredMethod(setterName(fieldName), joinTargetClass).invoke(entity, foundTarget);
            return entity;
        } catch (Exception e) {
            throw new EidaException(e);
        }
    }

    public List<T> list(Predicate<T> where) {
        return listAll().stream().filter(where).collect(toList());
    }

    public List<T> joinList(Predicate<T> where, String fieldName) {
        return list(where).stream()
                .map(entity -> join(entity, fieldName))
                .collect(toList());
    }

}
