package org.outofoffice.eidaprototype.lib.testing.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.outofoffice.eidaprototype.lib.core.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class TestEidaRepositoryTest {

    private TestEidaRepository repository;


    @BeforeEach
    void setup() {
        EidaDllGenerator dllGenerator = new EidaDllGenerator();
        EidaDmlGenerator dmlGenerator = new EidaDmlGenerator();

        EidaManagerClient managerClient = new EidaManagerClientImpl(dllGenerator, "http://manager:1234");
        EidaShardClient shardClient = new EidaShardClientImpl(dmlGenerator);
        EidaSerializer serializer = new EidaSerializerImpl();

        repository = new TestEidaRepository();
        repository.init(managerClient, shardClient, serializer);
    }

    @AfterEach
    void clear() {
        repository.deleteAll();
    }


    @Test
    void insert() {
        TestEidaEntity entity = new TestEidaEntity(1L, "name");

        Executable inserting = () -> repository.insert(entity);

        assertDoesNotThrow(inserting);
    }

    @Test
    void find() {
        TestEidaEntity expected = new TestEidaEntity(1L, "testName");

        TestEidaEntity found = repository.find(1L)
                .orElseThrow(NoSuchElementException::new);

        assertThat(found).isEqualTo(expected);
    }

    @Test
    void listAll() {
        List<TestEidaEntity> expected = List.of(
                new TestEidaEntity(1L, "testName1"), new TestEidaEntity(2L, "testName2"),
                new TestEidaEntity(1L, "testName1"), new TestEidaEntity(2L, "testName2"));

        List<TestEidaEntity> found = repository.listAll();

        assertThat(found).isEqualTo(expected);
    }

    @Test
    void list() {
        List<TestEidaEntity> expected = List.of(new TestEidaEntity(1L, "testName1"), new TestEidaEntity(1L, "testName1"));

        List<TestEidaEntity> found1 = repository.list(e -> e.getId() == 1L);
        assertThat(found1).isEqualTo(expected);

        List<TestEidaEntity> found2 = repository.list(e -> e.getId() == 3L);
        assertThat(found2).isEmpty();
    }
}
