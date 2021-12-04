package org.outofoffice.lib.testing.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.outofoffice.lib.core.client.EidaDllClient;
import org.outofoffice.lib.core.client.EidaDmlClient;
import org.outofoffice.lib.core.client.EidaManagerClient;
import org.outofoffice.lib.core.client.EidaShardClient;
import org.outofoffice.lib.core.query.EidaDllGenerator;
import org.outofoffice.lib.core.query.EidaDmlGenerator;
import org.outofoffice.lib.core.socket.EidaInMemoryClient;
import org.outofoffice.lib.core.ui.EidaSerializer;
import org.outofoffice.lib.example.TestEidaEntity;
import org.outofoffice.lib.example.TestEidaRepository;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TestEidaRepositoryTest {

    TestEidaRepository repository;

    EidaInMemoryClient inMemoryClient;
    String managerServerUrl = "http://manager:1234";


    @BeforeEach
    void setup() {
        EidaDllGenerator dllGenerator = new EidaDllGenerator();
        EidaDmlGenerator dmlGenerator = new EidaDmlGenerator();

        inMemoryClient = new EidaInMemoryClient();
        EidaDllClient managerClient = new EidaManagerClient(dllGenerator, inMemoryClient, managerServerUrl);
        EidaDmlClient shardClient = new EidaShardClient(dmlGenerator, inMemoryClient);
        EidaSerializer serializer = new EidaSerializer();

        repository = new TestEidaRepository();
        repository.init(managerClient, shardClient, serializer);
    }

    @AfterEach
    void clear() {
        repository.deleteAll();
    }


    @Test
    void insert() {
        inMemoryClient.put(managerServerUrl, "get dst TestEidaEntity", "http://shard1:1234");
        inMemoryClient.put("http://shard1:1234", "insert TestEidaEntity id,name 1,name", "");

        TestEidaEntity entity = new TestEidaEntity(1L, "name");

        Executable inserting = () -> repository.insert(entity);

        assertDoesNotThrow(inserting);
    }

    @Test
    void find() {
        inMemoryClient.put(managerServerUrl, "get src TestEidaEntity 1", "http://shard1:1234");
        inMemoryClient.put("http://shard1:1234", "select TestEidaEntity 1", "id,name\n1,testName");

        TestEidaEntity expected = new TestEidaEntity(1L, "testName");

        TestEidaEntity found = repository.find(1L)
                .orElseThrow(NoSuchElementException::new);

        assertThat(found).isEqualTo(expected);
        // assertEquals(found, expected);
    }

    @Test
    void listAll() {
        inMemoryClient.put(managerServerUrl, "get all TestEidaEntity", "http://shard01:1234,http://shard02:1234");
        inMemoryClient.put("http://shard01:1234", "select TestEidaEntity", "id,name\n1,testName1\n2,testName2");
        inMemoryClient.put("http://shard02:1234", "select TestEidaEntity", "id,name\n3,testName3\n4,testName4");

        List<TestEidaEntity> expected = List.of(
                new TestEidaEntity(1L, "testName1"), new TestEidaEntity(2L, "testName2"),
                new TestEidaEntity(3L, "testName3"), new TestEidaEntity(4L, "testName4"));

        List<TestEidaEntity> found = repository.listAll();

        assertThat(found).isEqualTo(expected);
    }

    @Test
    void list() {
        inMemoryClient.put(managerServerUrl, "get all TestEidaEntity", "http://shard01:1234,http://shard02:1234");
        inMemoryClient.put("http://shard01:1234", "select TestEidaEntity", "id,name\n1,kemi\n2,josh");
        inMemoryClient.put("http://shard02:1234", "select TestEidaEntity", "id,name\n3,kemi\n4,kemi");

        List<TestEidaEntity> found = repository.list(e -> e.getName().equals("kemi"));
        assertThat(found).hasSize(3);
    }

}
