package org.outofoffice.lib.context;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.outofoffice.lib.core.client.EidaDllClient;
import org.outofoffice.lib.core.client.EidaDmlClient;
import org.outofoffice.lib.core.client.EidaManagerClient;
import org.outofoffice.lib.core.client.EidaShardClient;
import org.outofoffice.lib.core.query.EidaDllGenerator;
import org.outofoffice.lib.core.query.EidaDmlGenerator;
import org.outofoffice.common.socket.EidaSocketClient;
import org.outofoffice.lib.core.ui.EidaEntity;
import org.outofoffice.lib.core.ui.EidaRepository;
import org.outofoffice.lib.core.ui.EidaSerializer;
import org.outofoffice.common.exception.EidaException;
import org.outofoffice.lib.util.ClassUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@NoArgsConstructor(access = PRIVATE)
public class EidaContext {

    private static EidaPropertyLoader propertyLoader;
    private static EidaRepositoryScanner repositoryScanner;

    private static final Map<Class<? extends EidaEntity<?>>, EidaRepository<? extends EidaEntity<?>, ?>> MAP = new HashMap<>();

    public static EidaRepository<? extends EidaEntity<?>, ?> getRepository(Class<? extends EidaEntity<?>> entityClass) {
        return MAP.get(entityClass);
    }

    public static Collection<EidaRepository<? extends EidaEntity<?>, ?>> getRepositories(){
        return MAP.values();
    }


    public static void init(Class<?> mainClass, EidaSocketClient socket) {
        log.info("Initialize Starting: socket - {}", socket);
        propertyLoader = new EidaPropertyLoader(mainClass);
        repositoryScanner = new EidaRepositoryScanner(mainClass);
        try {
            doInit(socket);
        } catch (Exception e) {
            throw new EidaException(e);
        }
        log.info("Initialize Finished");
    }

    private static <T extends EidaEntity<ID>, ID> void doInit(EidaSocketClient socket) throws Exception {
        String managerServerUrl = propertyLoader.getManagerServerUrl();

        EidaDllClient managerClient = new EidaManagerClient(new EidaDllGenerator(), socket, managerServerUrl);
        EidaDmlClient shardClient = new EidaShardClient(new EidaDmlGenerator(), socket);
        EidaSerializer serializer = new EidaSerializer();

        List<String> repositoryClassNames = repositoryScanner.scan();

        for (String className : repositoryClassNames) {
            Class<EidaRepository<T, ID>> repositoryClass = (Class<EidaRepository<T, ID>>) Class.forName(className);
            Class<? extends EidaEntity<ID>> entityClass = ClassUtils.toEntityClass(repositoryClass);
            MAP.put(entityClass, repositoryInstance(repositoryClass, managerClient, shardClient, serializer));
        }
    }

    private static <T extends EidaEntity<ID>, ID> EidaRepository<T, ID> repositoryInstance(
            Class<EidaRepository<T, ID>> repositoryClass, EidaDllClient managerClient, EidaDmlClient shardClient, EidaSerializer serializer
    ) throws Exception {
        EidaRepository<T, ID> repository = repositoryClass.getDeclaredConstructor().newInstance();
        repository.init(managerClient, shardClient, serializer);
        return repository;
    }

}
