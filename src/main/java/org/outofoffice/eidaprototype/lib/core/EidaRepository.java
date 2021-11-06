package org.outofoffice.eidaprototype.lib.core;

import org.outofoffice.eidaprototype.lib.util.ClassUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;


public abstract class EidaRepository<T extends EidaEntity<ID>, ID> {

    private EidaManagerClient managerClient;
    private EidaShardClient shardClient;
    private EidaSerializer serializer;


    public void init(EidaManagerClient managerClient, EidaShardClient shardClient, EidaSerializer serializer) {
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
    }

    public void update(T entity) {
    }

    public void delete(ID id) {
    }

    public void deleteAll() {
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

        StringBuilder response = new StringBuilder();

        for (String shardUrl : allShardUrls) {
            String tableString = shardClient.selectAll(tableName(), shardUrl);
            int index = tableString.indexOf("\n");

            String firstLine = tableString.substring(0, index);
            if (header == null) {
                header = firstLine;
                response.append(header);
            } else {
                if (!header.equals(firstLine)) throw new EidaException("샤드간 헤더 불일치");
            }
            String bodyString = tableString.substring(index);
            response.append(bodyString);
        }

        return serializer.deserialize(response.toString(), entityClass());
    }

    public <R> Optional<T> joinFind(ID id, Function<T, R> on) {
        return Optional.empty();
    }

    public List<T> list(Predicate<T> where) {
        return listAll().stream().filter(where).collect(toList());
    }

    public <R> List<T> joinList(Predicate<T> where, Function<T, R> on) {
        return List.of();
    }

}