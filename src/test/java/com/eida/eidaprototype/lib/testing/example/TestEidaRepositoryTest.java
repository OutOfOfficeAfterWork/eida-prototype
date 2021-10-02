package com.eida.eidaprototype.lib.testing.example;

import com.eida.eidaprototype.lib.core.EidaManagerClient;
import com.eida.eidaprototype.lib.core.EidaSerializer;
import com.eida.eidaprototype.lib.core.EidaShardClient;
import com.eida.eidaprototype.lib.testing.mock.ConsoleManagerClient;
import com.eida.eidaprototype.lib.testing.mock.ConsoleSerializer;
import com.eida.eidaprototype.lib.testing.mock.ConsoleShardClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class TestEidaRepositoryTest {

    private TestEidaRepository repository;


    @BeforeEach
    void setup() {
        EidaManagerClient managerClient = new ConsoleManagerClient("http://manager:1234");
        EidaShardClient shardClient = new ConsoleShardClient();
        EidaSerializer serializer = new ConsoleSerializer();

        repository = new TestEidaRepository();
        repository.init(managerClient, shardClient, serializer);
    }

    @AfterEach
    void clear() {
        repository.deleteAll();
    }


    @Test
    void insert() {
        TestEidaEntity entity = new TestEidaEntity(1L);

        Executable inserting = () -> repository.insert(entity);

        assertDoesNotThrow(inserting);
    }

    @Test
    void find() {
        TestEidaEntity expected = new TestEidaEntity(1L);

        TestEidaEntity found = repository.find(1L)
                .orElseThrow(NoSuchElementException::new);

        assertThat(found).isEqualTo(expected);
    }

}
