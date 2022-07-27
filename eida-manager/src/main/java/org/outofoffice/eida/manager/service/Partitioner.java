package org.outofoffice.eida.manager.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.exception.TableNotFoundException;
import org.outofoffice.eida.common.table.TableRepository;
import org.outofoffice.eida.manager.domain.ShardMapping;
import org.outofoffice.eida.manager.repository.ShardMappingRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;


@RequiredArgsConstructor
public class Partitioner {
    private final TableRepository tableRepository;
    private final ShardMappingRepository shardMappingRepository;

    private final ConcurrentMap<String, PriorityQueue<ShardElement>> tableQueueMap = new ConcurrentHashMap<>();

    public void init() {
        ShardMapping shardMapping = shardMappingRepository.find();
        Set<Integer> allShardIds = shardMapping.getAllShardIds();
        tableRepository.findAll()
            .forEach(table -> {
                String tableName = table.getTableName();
                Map<String, String> entityShardMap = table.copyContent();

                // count entity by shard
                Map<Integer, Integer> shardCountMap = new HashMap<>();
                entityShardMap.forEach((entity, row) -> {
                    Integer shard = Integer.parseInt(row.split(",")[1]);
                    int currEntityCountInShard = shardCountMap.getOrDefault(shard, 0);
                    shardCountMap.put(shard, currEntityCountInShard + 1);
                });

                // data for shard entity count info
                List<ShardElement> shardElementList = allShardIds.stream()
                    .map(shard -> {
                        int count = shardCountMap.getOrDefault(shard, 0);
                        return ShardElement.of(shard, count);
                    })
                    .collect(Collectors.toList());

                tableQueueMap.put(tableName, new PriorityQueue<>(shardElementList));
            });
    }

    public String nextShardId(String tableName) {
        PriorityQueue<ShardElement> priorityQueue = tableQueueMap.get(tableName);
        if (priorityQueue == null) throw new TableNotFoundException(new IllegalStateException(tableName));
        if (priorityQueue.isEmpty()) throw new IllegalStateException("pq empty");

        return priorityQueue.peek().getShardId()
            + ""; // TODO
    }

    public void arrange(String tableName) {
        PriorityQueue<ShardElement> priorityQueue = tableQueueMap.get(tableName);
        if (priorityQueue == null) throw new IllegalStateException("pq null");
        if (priorityQueue.isEmpty()) throw new IllegalStateException("pq empty");

        ShardElement shardElement = priorityQueue.poll();
        shardElement.addCount();
        priorityQueue.add(shardElement);
    }


    public void addTableQueue(String tableName) {
        ShardMapping shardMapping = shardMappingRepository.find();
        Set<Integer> allShardIds = shardMapping.getAllShardIds();

        List<ShardElement> shardElementList = allShardIds.stream()
            .map(ShardElement::empty)
            .collect(Collectors.toList());

        tableQueueMap.put(tableName, new PriorityQueue<>(shardElementList));
    }

    public void renameTableQueue(String currentName, String nextName) {
        PriorityQueue<ShardElement> queue = tableQueueMap.get(currentName);
        tableQueueMap.put(nextName, queue);
    }

    public void deleteTableQueue(String tableName) {
        tableQueueMap.remove(tableName);
    }


    @Data
    @AllArgsConstructor(access = PRIVATE)
    private static class ShardElement implements Comparable<ShardElement> {
        private final int shardId;
        private int rowCount;

        public void addCount() {
            rowCount++;
        }

        @Override
        public int compareTo(ShardElement o) {
            int compareCount = Integer.compare(rowCount, o.rowCount);
            return (compareCount != 0)
                ? compareCount
                : Integer.compare(shardId, o.shardId);
        }


        public static ShardElement of(int shardId, int rowCount) {
            return new ShardElement(shardId, rowCount);
        }

        public static ShardElement empty(int shardId) {
            return new ShardElement(shardId, 0);
        }

    }

}
