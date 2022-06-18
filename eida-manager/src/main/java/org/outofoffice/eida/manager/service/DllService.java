package org.outofoffice.eida.manager.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.exception.RowNotFoundException;
import org.outofoffice.eida.common.table.Table;
import org.outofoffice.eida.common.table.TableService;
import org.outofoffice.eida.manager.domain.ShardMapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;


@RequiredArgsConstructor
public class DllService {

    private final TableService tableService;
    private final ShardMappingService shardMappingService;

    private final Partitioner partitioner;


    public List<String> getAllShardUrls(String tableName) {
        Table table = tableService.findByName(tableName);
        Map<String, String> content = table.copyContent();
        Set<String> shardIds = content.values().stream()
            .map(line -> line.split(",")[1])
            .collect(toSet());
        ShardMapping shardMapping = shardMappingService.find();
        return shardMapping.getShardUrls(shardIds);
    }

    public String getDestinationShardUrl(String tableName) {
        String shardId = partitioner.nextShardId(tableName);
        ShardMapping shardMapping = shardMappingService.find();
        return shardMapping.getShardUrl(shardId).orElseThrow();
    }

    public String getSourceShardUrl(String tableName, String entityId) {
        Table table = tableService.findByName(tableName);
        Optional<String> oRow = table.getRow(entityId);
        if (oRow.isEmpty()) return "";

        String shardId = oRow.get().split(",")[1];
        ShardMapping shardMapping = shardMappingService.find();
        return shardMapping.getShardUrl(shardId).orElseThrow();
    }

    public void reportInsert(String shardUrl, String tableName, String id) {
        ShardMapping shardMapping = shardMappingService.find();
        String shardId = shardMapping.getShardId(shardUrl).orElseThrow();
        tableService.appendRow(tableName, id, shardId);
        partitioner.arrange(tableName);
    }

    public void reportDelete(String tableName, String id) {
        Table table = tableService.findByName(tableName);
        if (table.getRow(id).isEmpty()) throw new RowNotFoundException(tableName, id);
        tableService.deleteRow(tableName, id);
    }

}
