package org.outofoffice.eidaprototype.lib.testing.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.outofoffice.eidaprototype.lib.core.bean.EidaContext;
import org.outofoffice.eidaprototype.lib.core.socket.EidaInMemoryClient;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;


class KemiRepositoryTest {

    KemiRepository kemiRepository;

    String managerServerUrl = "http://manager:1234";
    EidaInMemoryClient socketClient = new EidaInMemoryClient();


    @BeforeEach
    void init() {
        EidaContext.init(socketClient);
        kemiRepository = (KemiRepository) EidaContext.MAP.get(KemiEntity.class);
    }

    @AfterEach
    void clear() {
        socketClient.clear();
    }


    @Test
    void find() {
        socketClient.put(managerServerUrl, "dll query", "http://shard1:1234");
        socketClient.put("http://shard1:1234", "dml query", "id,major,testEidaEntity(join)\n1,cs,2");

        KemiEntity expected = new KemiEntity(1L, "cs", new TestEidaEntity(2L, null));

        KemiEntity found = kemiRepository.find(1L)
                .orElseThrow(NoSuchElementException::new);

        assertThat(found).isEqualTo(expected);
    }

    @Test
        // TODO refactor...
    void join() {
        socketClient.put(managerServerUrl, "dll query", "http://shard1:1234");
        socketClient.put("http://shard1:1234", "dml query1", "id,major,testEidaEntity(join)\n1,cs,2");
        socketClient.put("http://shard1:1234", "dml query2", "id,name\n2,josh");

        KemiEntity expected = new KemiEntity(1L, "cs", new TestEidaEntity(2L, "josh"));

        KemiEntity found = kemiRepository.joinFind(1L, "testEidaEntity")
                .orElseThrow(NoSuchElementException::new);

        assertThat(found).isEqualTo(expected);
    }

}