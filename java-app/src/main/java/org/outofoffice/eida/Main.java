package org.outofoffice.eida;

import org.outofoffice.lib.context.EidaContext;
import org.outofoffice.lib.core.socket.EidaInMemoryClient;

public class Main {
    public static void main(String[] args) {
        EidaContext.init(new EidaInMemoryClient());
    }
}
