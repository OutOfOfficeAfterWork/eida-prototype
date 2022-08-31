package org.outofoffice.eida.manager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.outofoffice.eida.common.io.EidaFileFacade;
import org.outofoffice.eida.common.io.EidaTestFileFacade;
import org.outofoffice.eida.common.table.TableService;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;


class DllServiceTest {

    EidaFileFacade eidaFileFacade = new EidaTestFileFacade();

    DllService dllService;
    TableService tableService;
    SchemeService schemeService;
    ShardMappingService shardMappingService;
    Partitioner partitioner;

    String header = "teamId,teamName";


    @BeforeEach
    void setup() {
        eidaFileFacade.create("/scheme-path-file");
        eidaFileFacade.create("/shard-mapping-file");

        tableService = new TableService("/table-directory", eidaFileFacade);
        schemeService = new SchemeService("/scheme-path-file", eidaFileFacade);
        shardMappingService = new ShardMappingService("/shard-mapping-file", eidaFileFacade);

        partitioner = new Partitioner(tableService, shardMappingService);
        dllService = new DllService(tableService, shardMappingService, schemeService, partitioner);

        schemeService.save("Team", header);
    }


    @Test
    void getAllShardUrls() {
        shardMappingService.appendRow("localhost:10830");
        shardMappingService.appendRow("localhost:10831");
        shardMappingService.appendRow("localhost:10832");
        String tableName = "Team";

        tableService.create(tableName);
        tableService.appendRow(tableName, "1", "1");
        tableService.appendRow(tableName, "2", "2");

        String[] response = dllService.getShardUrlsAndScheme(tableName).split("\n");
        List<String> shardUrls = Arrays.stream(response[0].split(",")).collect(toList());
        String schemeString = response[1];
        assertThat(shardUrls).isEqualTo(List.of("localhost:10830", "localhost:10831"));
        assertThat(schemeString).isEqualTo(header);
    }

    @Test
    void getDestinationShardUrl() {
        String tableName = "Team";
        shardMappingService.appendRow("localhost:10830");
        shardMappingService.appendRow("localhost:10831");

        tableService.create(tableName);
        tableService.appendRow(tableName, "e1", "1");

        partitioner.init();

        String shardUrl = dllService.getDestination(tableName);
        assertThat(shardUrl).isEqualTo("localhost:10831");
    }

    @Test
    void getSourceShardUrl() {
        shardMappingService.appendRow("localhost:10830");

        String tableName = "Team";
        String id = "1";

        tableService.create(tableName);
        tableService.appendRow(tableName, id, "1");

        String[] response = dllService.getShardUrlsAndScheme(tableName).split("\n");
        List<String> shardUrls = Arrays.stream(response[0].split(",")).collect(toList());
        String schemeString = response[1];
        assertThat(shardUrls).isEqualTo(List.of("localhost:10830"));
        assertThat(schemeString).isEqualTo(header);
    }

    @Test
    void reportInsertShardUrl() {
        String shardUrl = "localhost:10830";
        String tableName = "Team";
        String id = "1";
        String shardId = "1";
        shardMappingService.appendRow(shardUrl);

        tableService.create(tableName);
        tableService.appendRow(tableName, id, shardId);

        partitioner.init();

        dllService.reportInsert(shardUrl, tableName, id);

        String[] response = dllService.getShardUrlsAndScheme(tableName).split("\n");
        List<String> shardUrls = Arrays.stream(response[0].split(",")).collect(toList());
        String schemeString = response[1];
        assertThat(shardUrls).isEqualTo(List.of(shardUrl));
        assertThat(schemeString).isEqualTo(header);
    }

    @Test
    void reportDeleteShardUrl() {
        String shardUrl = "localhost:10830";
        String tableName = "Team";
        String id = "1";
        String shardId = "1";

        tableService.create(tableName);
        tableService.appendRow(tableName, id, shardId);

        shardMappingService.appendRow(shardUrl);

        partitioner.init();
        dllService.reportInsert(shardUrl, tableName, id);

        dllService.reportDelete(tableName, id);

        String[] response = dllService.getSource(tableName, id).split("\n");
        String sourceShardUrl = response[0];
        String scheme = response[1];
        assertThat(sourceShardUrl).isEmpty();
        assertThat(scheme).isEqualTo(header);
    }

}
