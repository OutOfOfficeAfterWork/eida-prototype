package org.outofoffice.eida.manager.infrastructure;

import org.outofoffice.eida.manager.domain.ShardMapping;
import org.outofoffice.eida.manager.repository.ShardMappingRepository;

public class ShardMappingFileRepository implements ShardMappingRepository {
    @Override
    public ShardMapping find() {
        throw new IllegalStateException("todo impl");
    }

    @Override
    public void save(ShardMapping shardMapping) {
        throw new IllegalStateException("todo impl");
    }
}
