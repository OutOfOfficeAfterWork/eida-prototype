package org.outofoffice.eida.manager.repository;

import org.outofoffice.eida.manager.domain.ShardMapping;

public interface ShardMappingRepository {
    ShardMapping find();

    void save(ShardMapping shardMapping);
}
