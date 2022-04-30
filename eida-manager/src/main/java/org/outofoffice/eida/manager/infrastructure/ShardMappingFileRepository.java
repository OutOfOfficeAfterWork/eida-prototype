package org.outofoffice.eida.manager.infrastructure;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.domain.ShardMapping;
import org.outofoffice.eida.manager.repository.ShardMappingRepository;

@RequiredArgsConstructor
public class ShardMappingFileRepository implements ShardMappingRepository {
    private final ShardMappingFileFacade shardMappingFileFacade;

    @Override
    public ShardMapping find() {
        return shardMappingFileFacade.find();
    }

    @Override
    public void save(ShardMapping shardMapping) {
        shardMappingFileFacade.save(shardMapping);
    }
}
