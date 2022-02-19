package org.outofoffice.eida.manager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.outofoffice.eida.common.exception.RowNotFoundException;
import org.outofoffice.eida.manager.repository.MetadataFileRepository;
import org.outofoffice.eida.manager.repository.MetadataRepository;
import org.outofoffice.eida.manager.repository.TableFileRepository;
import org.outofoffice.eida.manager.repository.TableRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DllServiceTest {

    DllService dllService;
    TableRepository tableRepository;
    MetadataRepository metadataRepository;

    @BeforeEach
    void setup() {
        tableRepository = new TableFileRepository();
        metadataRepository = new MetadataFileRepository();
        dllService = new DllService(tableRepository, metadataRepository);
    }


    @Test
    void getAllShardUrls() {
        String tableName = "Team";
        List<String> shardUrls = dllService.getAllShardUrls(tableName);
        assertThat(shardUrls).isEqualTo(List.of("localhost:10830"));
    }

    @Test
    void getDestinationShardUrl() {
        String tableName = "Team";
        String shardUrl = dllService.getDestinationShardUrl(tableName);
        assertThat(shardUrl).isEqualTo("localhost:10830");
    }

    @Test
    void getSourceShardUrl() {
        String tableName = "Team";
        String id = "1";
        String sourceShardUrl = dllService.getSourceShardUrl(tableName, id);

        assertThat(sourceShardUrl).isEqualTo("localhost:10830");
    }

    @Test
    void reportInsertShardUrl() {
        String shardUrl = "localhost:10830";
        String tableName = "Team";
        String id = "1";
        dllService.reportInsertShardUrl(shardUrl, tableName, id);

        String sourceShardUrl = dllService.getSourceShardUrl(tableName, id);
        assertThat(sourceShardUrl).isEqualTo(shardUrl);
    }

    @Test
    void reportDeleteShardUrl() {
        String shardUrl = "localhost:10830";
        String tableName = "Team";
        String id = "1";
        dllService.reportDeleteShardUrl(shardUrl, tableName, id);

        Executable action = () -> dllService.getSourceShardUrl(tableName, id);
        assertThrows(RowNotFoundException.class, action);
    }

}
