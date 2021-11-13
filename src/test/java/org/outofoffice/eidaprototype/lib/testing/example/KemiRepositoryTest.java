package org.outofoffice.eidaprototype.lib.testing.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.outofoffice.eidaprototype.lib.core.client.EidaDllClient;
import org.outofoffice.eidaprototype.lib.core.client.EidaDmlClient;
import org.outofoffice.eidaprototype.lib.core.client.EidaManagerClient;
import org.outofoffice.eidaprototype.lib.core.client.EidaShardClient;
import org.outofoffice.eidaprototype.lib.core.query.EidaDllGenerator;
import org.outofoffice.eidaprototype.lib.core.query.EidaDmlGenerator;
import org.outofoffice.eidaprototype.lib.core.socket.EidaDefaultSocketClient;
import org.outofoffice.eidaprototype.lib.core.socket.EidaSocketClient;
import org.outofoffice.eidaprototype.lib.core.socket.EidaSocketClientLoggingProxy;
import org.outofoffice.eidaprototype.lib.core.ui.EidaSerializer;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class KemiRepositoryTest {

    KemiRepository kemiRepository = new KemiRepository();

    EidaDllClient managerClient;
    EidaDmlClient shardClient;

    @BeforeEach
    void init() {
        EidaDllGenerator dllGenerator = new EidaDllGenerator();
        EidaDmlGenerator dmlGenerator = new EidaDmlGenerator();

        managerClient = new EidaManagerClient(dllGenerator, null, "http://manager:1234");
        shardClient = new EidaShardClient(dmlGenerator, null);

        EidaSerializer serializer = new EidaSerializer();

        kemiRepository = new KemiRepository();
        kemiRepository.init(managerClient, shardClient, serializer);
    }

    @Test
    void find() {
        managerClient.useMockClient((address, message) -> "http://shard1:1234");
        shardClient.useMockClient((address, message) -> "id,major,testEidaEntity(join)\n1,cs,2");

        KemiEntity expected = new KemiEntity(1L, "cs", new TestEidaEntity(2L, null));

        KemiEntity found = kemiRepository.find(1L)
            .orElseThrow(NoSuchElementException::new);

        assertThat(found).isEqualTo(expected);
    }

}