package org.outofoffice.eidaprototype.lib.core.bean;

import org.outofoffice.eidaprototype.lib.core.client.EidaDllClient;
import org.outofoffice.eidaprototype.lib.core.client.EidaDmlClient;
import org.outofoffice.eidaprototype.lib.core.client.EidaManagerClient;
import org.outofoffice.eidaprototype.lib.core.client.EidaShardClient;
import org.outofoffice.eidaprototype.lib.core.query.EidaDllGenerator;
import org.outofoffice.eidaprototype.lib.core.query.EidaDmlGenerator;
import org.outofoffice.eidaprototype.lib.core.socket.EidaSocketClient;
import org.outofoffice.eidaprototype.lib.core.ui.EidaEntity;
import org.outofoffice.eidaprototype.lib.core.ui.EidaRepository;
import org.outofoffice.eidaprototype.lib.core.ui.EidaSerializer;
import org.outofoffice.eidaprototype.lib.exception.EidaException;
import org.outofoffice.eidaprototype.lib.testing.example.KemiRepository;
import org.outofoffice.eidaprototype.lib.testing.example.TestEidaRepository;
import org.outofoffice.eidaprototype.lib.util.ClassUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EidaContext {

    public static Map<Class<? extends EidaEntity<?>>, EidaRepository<? extends EidaEntity<?>, ?>> MAP = new HashMap<>();


    public static <T extends EidaEntity<ID>, ID> void init(EidaSocketClient socket) {
        EidaSocketClient socketClient = socket;

        String managerServerUrl = "http://manager:1234";
        EidaDllClient managerClient = new EidaManagerClient(managerServerUrl, new EidaDllGenerator(), socketClient);
        EidaDmlClient shardClient = new EidaShardClient(new EidaDmlGenerator(), socketClient);
        EidaSerializer serializer = new EidaSerializer();

        List<String> repositoryClassNames = List.of(TestEidaRepository.class.getName(), KemiRepository.class.getName());
        try {
            for (String className : repositoryClassNames) {
                Class<EidaRepository<T, ID>> repositoryClass = (Class<EidaRepository<T, ID>>) Class.forName(className);
                Class<? extends EidaEntity<ID>> entityClass = ClassUtils.toEntityClass(repositoryClass);
                EidaContext.MAP.put(entityClass, repositoryInstance(repositoryClass, managerClient, shardClient, serializer));
            }

        } catch (Exception e) {
            throw new EidaException(e);
        }
    }

    private static <T extends EidaEntity<ID>, ID> EidaRepository<T, ID> repositoryInstance(
            Class<EidaRepository<T, ID>> repositoryClass, EidaDllClient managerClient, EidaDmlClient shardClient, EidaSerializer serializer
    ) throws Exception {
        return repositoryClass.getDeclaredConstructor().newInstance().init(managerClient, shardClient, serializer);
    }

}
