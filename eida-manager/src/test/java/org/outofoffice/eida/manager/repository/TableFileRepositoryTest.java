package org.outofoffice.eida.manager.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.outofoffice.eida.manager.io.ManagerServerFileFacade;

import static org.assertj.core.api.Assertions.assertThat;

class TableFileRepositoryTest {

    TableFileRepository tableFileRepository;

    @BeforeEach
    void setup() {
        tableFileRepository = new TableFileRepository();
    }


    @Test
    void findShardIdByTableNameAndId() {
        ManagerServerFileFacade.createTableFile("Team");

        String shardId = tableFileRepository.findShardIdByTableNameAndId("Team", "1");
        assertThat(shardId).isEqualTo("1");
    }

}