package org.outofoffice.eidaprototype.lib.testing.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.outofoffice.eidaprototype.lib.core.client.EidaDllClient;
import org.outofoffice.eidaprototype.lib.core.client.EidaDmlClient;
import org.outofoffice.eidaprototype.lib.core.client.EidaManagerClient;
import org.outofoffice.eidaprototype.lib.core.client.EidaShardClient;
import org.outofoffice.eidaprototype.lib.core.query.EidaDllGenerator;
import org.outofoffice.eidaprototype.lib.core.query.EidaDmlGenerator;
import org.outofoffice.eidaprototype.lib.core.socket.EidaInMemoryClient;
import org.outofoffice.eidaprototype.lib.core.ui.EidaSerializer;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;


class KemiRepositoryTest {

    KemiRepository kemiRepository = new KemiRepository();

    EidaInMemoryClient socketClient;
    String managerServerUrl = "http://manager:1234";


    @BeforeEach
    void init() {
        EidaDllGenerator dllGenerator = new EidaDllGenerator();
        EidaDmlGenerator dmlGenerator = new EidaDmlGenerator();

        socketClient = new EidaInMemoryClient();
        EidaDllClient managerClient = new EidaManagerClient(dllGenerator, socketClient);
        EidaDmlClient shardClient = new EidaShardClient(dmlGenerator, socketClient);
        managerClient.setManagerServerUrl(managerServerUrl);
        EidaSerializer serializer = new EidaSerializer();

        kemiRepository = new KemiRepository();
        kemiRepository.init(managerClient, shardClient, serializer);
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

        TestEidaRepository joinRepository = getTestEidaRepository();
        KemiEntity found = kemiRepository.joinFind(1L, KemiEntity::getTestEidaEntity, KemiEntity::setTestEidaEntity, joinRepository)
                .orElseThrow(NoSuchElementException::new);

        assertThat(found).isEqualTo(expected);
    }

    private TestEidaRepository getTestEidaRepository() {
        TestEidaRepository joinRepository = new TestEidaRepository();
        EidaDllGenerator dllGenerator = new EidaDllGenerator();
        EidaDmlGenerator dmlGenerator = new EidaDmlGenerator();
        EidaDllClient managerClient = new EidaManagerClient(dllGenerator, socketClient);
        EidaDmlClient shardClient = new EidaShardClient(dmlGenerator, socketClient);
        managerClient.setManagerServerUrl(managerServerUrl);
        EidaSerializer serializer = new EidaSerializer();
        joinRepository.init(managerClient, shardClient, serializer);
        return joinRepository;
    }

}