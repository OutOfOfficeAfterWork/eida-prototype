package org.outofoffice.eida.manager.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.outofoffice.eida.manager.domain.ShardMapping;
import org.outofoffice.eida.manager.infrastructure.ShardMappingMockRepository;
import org.outofoffice.eida.manager.repository.ShardMappingRepository;
import org.outofoffice.eida.common.table.Table;
import org.outofoffice.eida.common.table.TableMapRepository;
import org.outofoffice.eida.common.table.TableRepository;

import static org.assertj.core.api.Assertions.assertThat;

class PartitionerTest {

    Partitioner partitioner;

    TableRepository tableRepository;
    ShardMappingRepository shardMappingRepository;
    ShardMappingService shardMappingService;

    @BeforeEach
    void setup() {
        tableRepository = new TableMapRepository();
        shardMappingRepository = new ShardMappingMockRepository();
        partitioner = new Partitioner(tableRepository, shardMappingRepository);

        shardMappingRepository.save(new ShardMapping());
        shardMappingService = new ShardMappingService(shardMappingRepository);
    }

    @AfterEach
    void clear() {
        tableRepository.clear();
    }

    @Test
    void test() {
        String tableName = "TestTable";
        // shard1 : e1, e2
        // shard2 :

        Table table = new Table(tableName);
        table.appendRow("e1", "1");
        table.appendRow("e2", "1");
        tableRepository.save(table);

        shardMappingService.appendRow("1", "localhost:10830");
        shardMappingService.appendRow("2", "localhost:10831");

        partitioner.init();

        String s1 = partitioner.nextShardId(tableName); // shard2
        partitioner.arrange(tableName);

        String s2 = partitioner.nextShardId(tableName); // shard2
        partitioner.arrange(tableName);

        String s3 = partitioner.nextShardId(tableName); // shard1
        partitioner.arrange(tableName);

        assertThat(s1).isEqualTo("2");
        assertThat(s2).isEqualTo("2");
        assertThat(s3).isEqualTo("1");
    }

}