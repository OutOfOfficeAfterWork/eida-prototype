package org.outofoffice.lib.testing.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.outofoffice.lib.context.EidaContext;
import org.outofoffice.lib.core.query.EidaDmlGenerator;
import org.outofoffice.common.socket.EidaInMemoryClient;
import org.outofoffice.lib.example.KemiEntity;
import org.outofoffice.lib.example.KemiRepository;
import org.outofoffice.lib.example.TestEidaEntity;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;


class KemiRepositoryTest {

    KemiRepository kemiRepository = new KemiRepository();

    EidaInMemoryClient socketClient;
    String managerServerUrl = "http://manager:1234";
    String shardUrl = "http://shard1:1234";


    @BeforeEach
    void init() {
        socketClient = new EidaInMemoryClient();

        EidaContext.init(KemiRepository.class, socketClient);
        kemiRepository = (KemiRepository) EidaContext.getRepository(KemiEntity.class);
    }

    @Test
    void find() {
        socketClient.put(managerServerUrl, "get src, KemiEntity 1", "http://shard1:1234");

        EidaDmlGenerator eidaDmlGenerator = new EidaDmlGenerator();
        String dml = eidaDmlGenerator.createSelectByIdQuery("KemiEntity", 1);
        socketClient.put("http://shard1:1234", dml, "id,major,testEidaEntity\n1,cs,2");

        KemiEntity expected = new KemiEntity(1L, "cs", new TestEidaEntity(2L, null));

        KemiEntity found = kemiRepository.find(1L)
                .orElseThrow(NoSuchElementException::new);

        assertThat(found).isEqualTo(expected);
    }

    @Test
    void join() {
        socketClient.put(managerServerUrl, "get src, KemiEntity 1", "http://shard1:1234");
        socketClient.put("http://shard1:1234", "select, KemiEntity 1", "id,major,testEidaEntity\n1,cs,2");
        socketClient.put(managerServerUrl, "get src, TestEidaEntity 2", "http://shard2:1234");
        socketClient.put("http://shard2:1234", "select, TestEidaEntity 2", "id,name\n2,josh");

        KemiEntity expected = new KemiEntity(1L, "cs", new TestEidaEntity(2L, "josh"));

        KemiEntity found = kemiRepository.joinFind(1L, "testEidaEntity")
                .orElseThrow(NoSuchElementException::new);

        assertThat(found).isEqualTo(expected);
    }

    @Test
    void joinList() {
        socketClient.put(managerServerUrl, "get all, KemiEntity", "http://shard01:1234,http://shard02:1234");
        socketClient.put("http://shard01:1234", "select all, KemiEntity", "id,major,testEidaEntity\n1,cs,2\n2,mathematics,3");
        socketClient.put("http://shard02:1234", "select all, KemiEntity", "id,major,testEidaEntity\n3,physics,4\n4,cs,5");
        socketClient.put(managerServerUrl, "get src, TestEidaEntity 2", "http://shard01:1234");
        socketClient.put(managerServerUrl, "get src, TestEidaEntity 5", "http://shard02:1234");
        socketClient.put("http://shard01:1234", "select, TestEidaEntity 2", "id,name\n2,kemi");
        socketClient.put("http://shard02:1234", "select, TestEidaEntity 5", "id,name\n5,josh");

        List<KemiEntity> found = kemiRepository.joinList(e -> e.getMajor().equals("cs"), "testEidaEntity");
        assertThat(found).hasSize(2);
        assertThat(found).isEqualTo(List.of(
                new KemiEntity(1L, "cs", new TestEidaEntity(2L, "kemi")),
                new KemiEntity(4L, "cs", new TestEidaEntity(5L, "josh"))));
    }

}