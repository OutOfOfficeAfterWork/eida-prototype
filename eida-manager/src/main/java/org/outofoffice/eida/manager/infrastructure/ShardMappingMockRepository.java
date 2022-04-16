package org.outofoffice.eida.manager.infrastructure;

import lombok.Setter;
import org.outofoffice.eida.manager.domain.ShardMapping;
import org.outofoffice.eida.manager.repository.ShardMappingRepository;

import static lombok.AccessLevel.PRIVATE;

public class ShardMappingMockRepository implements ShardMappingRepository {
    @Setter(PRIVATE)
    private ShardMapping shardMapping;

    public ShardMappingMockRepository() {
        shardMapping = new ShardMapping();
    }

    @Override
    public ShardMapping find() {
        return shardMapping;
    }

    @Override
    public void save(ShardMapping shardMapping) {
        setShardMapping(shardMapping);
    }
}
