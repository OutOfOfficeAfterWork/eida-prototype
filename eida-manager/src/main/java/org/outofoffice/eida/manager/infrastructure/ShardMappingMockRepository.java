package org.outofoffice.eida.manager.infrastructure;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.outofoffice.eida.manager.domain.ShardMapping;
import org.outofoffice.eida.manager.repository.ShardMappingRepository;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
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
        log.debug("post save: \n\t{}", String.join("\n\t", find().copyContent().values()));
    }
}
