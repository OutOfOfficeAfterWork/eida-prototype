package org.outofoffice.lib.testing.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.outofoffice.lib.core.client.EidaDllClient;
import org.outofoffice.lib.core.client.EidaDmlClient;
import org.outofoffice.lib.core.client.EidaManagerClient;
import org.outofoffice.lib.core.client.EidaShardClient;
import org.outofoffice.lib.core.query.EidaDllGenerator;
import org.outofoffice.lib.core.query.EidaDmlGenerator;
import org.outofoffice.lib.core.socket.EidaInMemoryClient;
import org.outofoffice.lib.core.ui.EidaSerializer;
import org.outofoffice.lib.example.KemiEntity;
import org.outofoffice.lib.example.KemiRepository;
import org.outofoffice.lib.example.TestEidaEntity;

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
        socketClient.put(managerServerUrl, "get src KemiEntity 1", "http://shard1:1234");

        EidaDmlGenerator eidaDmlGenerator = new EidaDmlGenerator();
        String dml = eidaDmlGenerator.createSelectByIdQuery("KemiEntity", 1);
        socketClient.put("http://shard1:1234", dml, "id,major,testEidaEntity(join)\n1,cs,2");

        KemiEntity expected = new KemiEntity(1L, "cs", new TestEidaEntity(2L, null));

        KemiEntity found = kemiRepository.find(1L)
                .orElseThrow(NoSuchElementException::new);

        assertThat(found).isEqualTo(expected);
    }

}