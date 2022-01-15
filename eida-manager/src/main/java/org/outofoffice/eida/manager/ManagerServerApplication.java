package org.outofoffice.eida.manager;

import lombok.extern.slf4j.Slf4j;
import org.outofoffice.eida.manager.core.ManagerServer;

@Slf4j
public class ManagerServerApplication {

    private static final int PORT = 10325;

    public static void main(String[] args) {
        new ManagerServer(PORT).run();
    }

}
