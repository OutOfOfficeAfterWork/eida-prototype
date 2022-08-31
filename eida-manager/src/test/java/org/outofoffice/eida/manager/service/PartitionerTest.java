package org.outofoffice.eida.manager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.outofoffice.eida.common.io.EidaFileFacade;
import org.outofoffice.eida.common.io.EidaTestFileFacade;
import org.outofoffice.eida.common.table.TableService;

import static org.assertj.core.api.Assertions.assertThat;

class PartitionerTest {

    Partitioner partitioner;

    EidaFileFacade eidaFileFacade = new EidaTestFileFacade();
    TableService tableService;
    ShardMappingService shardMappingService;

    @BeforeEach
    void setup() {
        eidaFileFacade.create("/shard-mapping-file");
        tableService = new TableService("/table-directory", eidaFileFacade);
        shardMappingService = new ShardMappingService("/shard-mapping-file", eidaFileFacade);
        partitioner = new Partitioner(tableService, shardMappingService);
    }


    @Test
    void test() {
        String tableName = "TestTable";
        // shard1 : e1, e2
        // shard2 :
        shardMappingService.appendRow("localhost:10830");
        shardMappingService.appendRow("localhost:10831");

        tableService.create(tableName);
        tableService.appendRow(tableName, "e1", "1");
        tableService.appendRow(tableName, "e2", "1");

        partitioner.init();

        int s1 = partitioner.nextShardId(tableName); // shard2
        partitioner.arrange(tableName);

        int s2 = partitioner.nextShardId(tableName); // shard2
        partitioner.arrange(tableName);

        int s3 = partitioner.nextShardId(tableName); // shard1
        partitioner.arrange(tableName);

        assertThat(s1).isEqualTo(2);
        assertThat(s2).isEqualTo(2);
        assertThat(s3).isEqualTo(1);
    }

}