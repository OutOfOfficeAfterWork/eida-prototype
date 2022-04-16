package org.outofoffice.lib.testing.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.outofoffice.lib.context.EidaContext;
import org.outofoffice.common.socket.EidaInMemoryClient;
import org.outofoffice.lib.example.TestEidaEntity;
import org.outofoffice.lib.example.TestEidaRepository;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class TestEidaRepositoryTest {

    TestEidaRepository repository;

    EidaInMemoryClient inMemoryClient;
    String managerServerUrl = "localhost:10325";


    @BeforeEach
    void setup() {
        inMemoryClient = new EidaInMemoryClient();

        EidaContext.init(TestEidaRepository.class, inMemoryClient);
        repository = (TestEidaRepository) EidaContext.getRepository(TestEidaEntity.class);
    }

    @Test
    void insert() {
        inMemoryClient.put(managerServerUrl, "get dst, TestEidaEntity", "http://shard1:1234");
        inMemoryClient.put("http://shard1:1234", "insert, TestEidaEntity id,name 1,name", "");
        inMemoryClient.put(managerServerUrl, "report insert, http://shard1:1234 TestEidaEntity 1", "");

        TestEidaEntity entity = new TestEidaEntity(1L, "name");

        Executable inserting = () -> repository.insert(entity);

        assertDoesNotThrow(inserting);
    }

    @Test
    void update() {
        inMemoryClient.put(managerServerUrl, "get src, TestEidaEntity 1", "http://shard1:1234");
        inMemoryClient.put("http://shard1:1234", "update, TestEidaEntity id,name 1,name", "");

        TestEidaEntity entity = new TestEidaEntity(1L, "name");

        Executable inserting = () -> repository.update(entity);

        assertDoesNotThrow(inserting);
    }

    @Test
    void delete() {
        inMemoryClient.put(managerServerUrl, "get src, TestEidaEntity 1", "http://shard1:1234");
        inMemoryClient.put("http://shard1:1234", "delete, TestEidaEntity 1", "");
        inMemoryClient.put(managerServerUrl, "report delete, http://shard1:1234 TestEidaEntity 1", "");

        Executable deleted = () -> repository.delete(1L);
        assertDoesNotThrow(deleted);
    }

    @Test
    void deleteAll() {
        inMemoryClient.put(managerServerUrl, "get all, TestEidaEntity", "http://shard1:1234,http://shard2:1234");
        inMemoryClient.put("http://shard1:1234", "select all, TestEidaEntity", "id,name\n1,kemi\n2,josh");
        inMemoryClient.put("http://shard2:1234", "select all, TestEidaEntity", "id,name\n3,luffy");

        inMemoryClient.put(managerServerUrl, "get src, TestEidaEntity 1", "http://shard1:1234");
        inMemoryClient.put("http://shard1:1234", "delete, TestEidaEntity 1", "");
        inMemoryClient.put(managerServerUrl, "report delete, http://shard1:1234 TestEidaEntity 1", "");
        inMemoryClient.put(managerServerUrl, "get src, TestEidaEntity 2", "http://shard1:1234");
        inMemoryClient.put("http://shard1:1234", "delete, TestEidaEntity 2", "");
        inMemoryClient.put(managerServerUrl, "report delete, http://shard1:1234 TestEidaEntity 2", "");
        inMemoryClient.put(managerServerUrl, "get src, TestEidaEntity 3", "http://shard2:1234");
        inMemoryClient.put("http://shard2:1234", "delete, TestEidaEntity 3", "");
        inMemoryClient.put(managerServerUrl, "report delete, http://shard2:1234 TestEidaEntity 3", "");

        Executable deleted = () -> repository.deleteAll();
        assertDoesNotThrow(deleted);
    }

    @Test
    void find() {
        inMemoryClient.put(managerServerUrl, "get src, TestEidaEntity 1", "http://shard1:1234");
        inMemoryClient.put("http://shard1:1234", "select, TestEidaEntity 1", "id,name\n1,testName");

        TestEidaEntity expected = new TestEidaEntity(1L, "testName");

        TestEidaEntity found = repository.find(1L)
                .orElseThrow(NoSuchElementException::new);

        assertThat(found).isEqualTo(expected);
        // assertEquals(found, expected);
    }

    @Test
    void listAll() {
        inMemoryClient.put(managerServerUrl, "get all, TestEidaEntity", "http://shard01:1234,http://shard02:1234");
        inMemoryClient.put("http://shard01:1234", "select all, TestEidaEntity", "id,name\n1,testName1\n2,testName2");
        inMemoryClient.put("http://shard02:1234", "select all, TestEidaEntity", "id,name\n3,testName3\n4,testName4");

        List<TestEidaEntity> expected = List.of(
                new TestEidaEntity(1L, "testName1"), new TestEidaEntity(2L, "testName2"),
                new TestEidaEntity(3L, "testName3"), new TestEidaEntity(4L, "testName4"));

        List<TestEidaEntity> found = repository.listAll();

        assertThat(found).isEqualTo(expected);
    }

    @Test
    void list() {
        inMemoryClient.put(managerServerUrl, "get all, TestEidaEntity", "http://shard01:1234,http://shard02:1234");
        inMemoryClient.put("http://shard01:1234", "select all, TestEidaEntity", "id,name\n1,kemi\n2,josh");
        inMemoryClient.put("http://shard02:1234", "select all, TestEidaEntity", "id,name\n3,kemi\n4,kemi");

        List<TestEidaEntity> found = repository.list(e -> e.getName().equals("kemi"));
        assertThat(found).hasSize(3);
    }

}
