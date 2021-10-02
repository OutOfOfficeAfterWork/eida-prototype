package com.eida.eidaprototype.lib.core;

import com.eida.eidaprototype.lib.util.ClassUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


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
        String shardUrl = managerClient.getShardUrl(tableName());
        shardClient.insert(shardUrl, tableName(), serialized);
    }

    public void update(T entity) {
    }

    public void delete(ID id) {
    }

    public void deleteAll() {
    }

    public Optional<T> find(ID id) {
        String shardUrl = managerClient.getShardUrl(tableName(), id);
        String serializable = shardClient.select(shardUrl, tableName(), id);
        T entity = serializer.deserialize(serializable, entityClass());
        return Optional.ofNullable(entity);
    }

    public List<T> findAll() {
        return List.of();
    }

    public <R> Optional<T> joinFind(ID id, Function<T, R> on) {
        return Optional.empty();
    }

    public List<T> list(Predicate<T> where) {
        return List.of();
    }

    public <R> List<T> joinList(Predicate<T> where, Function<T, R> on) {
        return List.of();
    }

}
