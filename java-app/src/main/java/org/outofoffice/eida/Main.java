package org.outofoffice.eida;

import org.outofoffice.common.socket.EidaSocketClient;
import org.outofoffice.lib.context.EidaContext;

public class Main {
    public static void main(String[] args) {
        EidaContext.init(Main.class, new EidaSocketClient());
    }
}
