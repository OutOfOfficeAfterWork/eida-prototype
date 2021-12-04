package org.outofoffice.lib.testing.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.outofoffice.lib.context.EidaContext;
import org.outofoffice.lib.core.query.EidaDmlGenerator;
import org.outofoffice.lib.core.socket.EidaInMemoryClient;
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
        socketClient = new EidaInMemoryClient();

        EidaContext.init(KemiRepository.class, socketClient);
        kemiRepository = (KemiRepository) EidaContext.getRepository(KemiEntity.class);
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