package org.outofoffice.eida.manager.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.outofoffice.eida.manager.io.ManagerServerFileFacade;

import static org.assertj.core.api.Assertions.assertThat;

class TableFileRepositoryTest {

    TableFileRepository tableFileRepository;

    @BeforeEach
    void setup() {
        ManagerServerFileFacade.createTableFile("Team");
        tableFileRepository = new TableFileRepository();
    }

    @AfterEach
    void clear (){
        ManagerServerFileFacade.deleteTableFile("Team");
    }


    @Test
    void findShardIdByTableNameAndId() {
        ManagerServerFileFacade.appendLineToTableFile("Team", "1", "1");
        String shardId = tableFileRepository.findShardIdByTableNameAndId("Team", "1");
        assertThat(shardId).isEqualTo("1");
    }

}
