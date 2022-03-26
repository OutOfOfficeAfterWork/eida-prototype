package org.outofoffice.eida.manager.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.outofoffice.eida.common.table.TableMapRepository;
import org.outofoffice.eida.common.table.TableRepository;
import org.outofoffice.eida.common.table.TableService;
import org.outofoffice.eida.common.exception.RowNotFoundException;
import org.outofoffice.eida.common.table.Table;
import org.outofoffice.eida.manager.repository.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DllServiceTest {

    DllService dllService;
    TableRepository tableRepository;
    TableService tableService;
    MetadataRepository metadataRepository;
    Partitioner partitioner;

    @BeforeEach
    void setup() {
        tableRepository = new TableMapRepository();
        tableService = new TableService(tableRepository);
        metadataRepository = new MetadataMapRepository();
        partitioner = new Partitioner(tableRepository, metadataRepository);
        dllService = new DllService(tableService, tableRepository, metadataRepository, partitioner);
    }

    @AfterEach
    void clear() {
        tableRepository.clear();
    }


    @Test
    void getAllShardUrls() {
        metadataRepository.save("1", "localhost:10830");
        metadataRepository.save("2", "localhost:10831");
        metadataRepository.save("3", "localhost:10832");
        String tableName = "Team";

        Table table = new Table(tableName);
        table.appendRow("1", "1");
        table.appendRow("2", "2");
        tableRepository.save(table);

        List<String> shardUrls = dllService.getAllShardUrls(tableName);
        assertThat(shardUrls).isEqualTo(List.of("localhost:10830", "localhost:10831"));
    }

    @Test
    void getDestinationShardUrl() {
        String tableName = "Team";
        metadataRepository.save("s1", "localhost:10830");
        metadataRepository.save("s2", "localhost:10831");

        Table table = new Table(tableName);
        table.appendRow("e1", "s1");
        tableRepository.save(table);

        partitioner.init();

        String shardUrl = dllService.getDestinationShardUrl(tableName);
        assertThat(shardUrl).isEqualTo("localhost:10831");
    }

    @Test
    void getSourceShardUrl() {
        metadataRepository.save("3", "localhost:10830");

        String tableName = "Team";
        String id = "1";

        Table table = new Table(tableName);
        table.appendRow(id, "3");
        tableRepository.save(table);

        String sourceShardUrl = dllService.getSourceShardUrl(tableName, id);

        assertThat(sourceShardUrl).isEqualTo("localhost:10830");
    }

    @Test
    void reportInsertShardUrl() {
        String shardUrl = "localhost:10830";
        String tableName = "Team";
        String id = "1";
        String shardId = "1";
        metadataRepository.save(shardId, shardUrl);

        Table table = new Table(tableName);
        table.appendRow(id, shardId);
        tableRepository.save(table);

        partitioner.init();

        dllService.reportInsert(shardUrl, tableName, id);

        String sourceShardUrl = dllService.getSourceShardUrl(tableName, id);
        assertThat(sourceShardUrl).isEqualTo(shardUrl);
    }

    @Test
    void reportDeleteShardUrl() {
        String shardUrl = "localhost:10830";
        String tableName = "Team";
        String id = "1";
        String shardId = "1";

        Table table = new Table(tableName);
        table.appendRow(id, shardId);
        tableRepository.save(table);
        metadataRepository.save(shardId, shardUrl);

        partitioner.init();
        dllService.reportInsert(shardUrl, tableName, id);

        dllService.reportDelete(tableName, id);

        Executable action = () -> dllService.getSourceShardUrl(tableName, id);
        assertThrows(RowNotFoundException.class, action);
    }

}
