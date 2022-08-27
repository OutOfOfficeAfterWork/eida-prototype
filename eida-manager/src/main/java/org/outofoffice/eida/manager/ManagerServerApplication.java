package org.outofoffice.eida.manager;

import lombok.extern.slf4j.Slf4j;
import org.outofoffice.eida.common.QueryDispatcher;
import org.outofoffice.eida.common.QueryHandlerMap;
import org.outofoffice.eida.common.ServerRunner;
import org.outofoffice.eida.manager.configuration.SingletonContainer;
import org.outofoffice.eida.manager.configuration.handler.ManagerServerQueryHandlerMap;

@Slf4j
public class ManagerServerApplication {

    private static final int PORT = 10325;

    public static void main(String[] args) {
        SingletonContainer.init();

        QueryHandlerMap queryHandlerMap = new ManagerServerQueryHandlerMap().configureMappings();
        QueryDispatcher queryDispatcher = new QueryDispatcher(queryHandlerMap);
        new ServerRunner(PORT, queryDispatcher).run();
    }

}
