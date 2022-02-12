package org.outofoffice.eida.manager.repository;

public interface MetadataRepository {
    String findShardUrlByShardId(String shardId);
}
