package org.outofoffice.eida.manager.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.domain.ShardMapping;
import org.outofoffice.eida.manager.repository.ShardMappingRepository;

@RequiredArgsConstructor
public class ShardMappingService {

    private final ShardMappingRepository shardMappingRepository;


    public void appendRow(String shardId, String shardUrl) {
        ShardMapping shardMapping = shardMappingRepository.find();
        shardMapping.appendRow(shardId, shardUrl);
        shardMappingRepository.save(shardMapping);
    }

    public ShardMapping find() {
        return shardMappingRepository.find();
    }

}
