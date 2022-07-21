package org.outofoffice.eida.manager.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.exception.TableNotFoundException;
import org.outofoffice.eida.manager.domain.ShardMapping;
import org.outofoffice.eida.manager.repository.ShardMappingRepository;
import org.outofoffice.eida.common.table.TableRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;

@RequiredArgsConstructor
public class Partitioner {
    private final TableRepository tableRepository;
    private final ShardMappingRepository shardMappingRepository;

    private final ConcurrentMap<String, PriorityQueue<ShardElement>> tableQueueMap = new ConcurrentHashMap<>();

    public void init() {
        ShardMapping shardMapping = shardMappingRepository.find();
        Set<String> allShardIds = shardMapping.getAllShardIds();
        tableRepository.findAll()
            .forEach(table -> {
                String tableName = table.getTableName();
                Map<String, String> entityShardMap = table.copyContent();

                // count entity by shard
                Map<String, Integer> shardCountMap = new HashMap<>();
                entityShardMap.forEach((entity, row) -> {
                    String shard = row.split(",")[1];
                    int currEntityCountInShard = shardCountMap.getOrDefault(shard, 0);
                    shardCountMap.put(shard, currEntityCountInShard + 1);
                });

                // data for shard entity count info
                List<ShardElement> shardElementList = allShardIds.stream()
                    .map(shard -> {
                        int count = shardCountMap.getOrDefault(shard, 0);
                        return new ShardElement(shard, count);
                    })
                    .collect(Collectors.toList());

                tableQueueMap.put(tableName, new PriorityQueue<>(shardElementList));
            });
    }

    public String nextShardId(String tableName) {
        PriorityQueue<ShardElement> priorityQueue = tableQueueMap.get(tableName);
        if (priorityQueue == null) throw new TableNotFoundException(new IllegalStateException(tableName));
        if (priorityQueue.isEmpty()) throw new IllegalStateException("pq empty");

        return priorityQueue.peek().getShardId();
    }

    public void arrange(String tableName) {
        PriorityQueue<ShardElement> priorityQueue = tableQueueMap.get(tableName);
        if (priorityQueue == null) throw new IllegalStateException("pq null");
        if (priorityQueue.isEmpty()) throw new IllegalStateException("pq empty");

        ShardElement shardElement = priorityQueue.poll();
        shardElement.addCount();
        priorityQueue.add(shardElement);
    }

    @Data
    @AllArgsConstructor
    private static class ShardElement implements Comparable<ShardElement> {
        private final String shardId;
        private int rowCount;

        public void addCount() {
            rowCount++;
        }

        @Override
        public int compareTo(ShardElement o) {
            int compareCount = Integer.compare(rowCount, o.rowCount);
            return (compareCount != 0)
                ? compareCount
                : String.CASE_INSENSITIVE_ORDER.compare(shardId, o.shardId);
        }
    }

}
