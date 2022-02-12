package org.outofoffice.eida.shard;

import lombok.extern.slf4j.Slf4j;
import org.outofoffice.eida.common.QueryDispatcher;
import org.outofoffice.eida.common.QueryHandlerMap;
import org.outofoffice.eida.common.ServerRunner;

@Slf4j
public class ShardServerApplication {

    private static final int PORT = 10830;

    public static void main(String[] args) {
        QueryHandlerMap queryHandlerMap = new ShardServerQueryHandlerMap().configureMappings();
        QueryDispatcher queryDispatcher = new QueryDispatcher(queryHandlerMap);
        new ServerRunner(PORT, queryDispatcher).run();
    }

}
