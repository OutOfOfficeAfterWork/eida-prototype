package org.outofoffice.eida.testing;

import org.outofoffice.common.socket.EidaSocketClient;
import org.outofoffice.lib.context.EidaContext;

public class Main {
    static EntityRepository entityRepository;
    static EntityService entityService;

    static {
        EidaContext.init(org.outofoffice.eida.testing.Main.class, new EidaSocketClient());

        entityRepository = (EntityRepository) EidaContext.getRepository(Entity.class);
        entityService = new EntityService(entityRepository);
    }
    public static void main(String[] args) {
        entityService.insert(Entity.of("k1", "v1"));
        entityService.insert(Entity.of("k2", "v2"));
        entityService.insert(Entity.of("k3", "v3"));
        entityService.insert(Entity.of("k4", "v4"));
        entityService.insert(Entity.of("k5", "v5"));
        entityService.insert(Entity.of("k6", "v6"));
    }
}
