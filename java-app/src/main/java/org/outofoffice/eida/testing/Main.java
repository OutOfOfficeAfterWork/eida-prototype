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
        entityService.insert(new Entity("k6", "v6"));
        entityService.insert(new Entity("k7", "v7"));
        entityService.insert(new Entity("k8", "v8"));
        entityService.insert(new Entity("k9", "v9"));
        entityService.insert(new Entity("k10", "v10"));
        entityService.insert(new Entity("k11", "v11"));
    }
}
