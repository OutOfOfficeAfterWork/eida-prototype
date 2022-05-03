package org.outofoffice.eida.shard;

import lombok.extern.slf4j.Slf4j;
import org.outofoffice.eida.common.QueryDispatcher;
import org.outofoffice.eida.common.QueryHandlerMap;
import org.outofoffice.eida.common.ServerRunner;
import org.outofoffice.eida.shard.configuration.SingletonContainer;
import org.outofoffice.eida.shard.configuration.handler.ShardServerQueryHandlerMap;

@Slf4j
public class ShardServerApplication {

    private static final int PORT = 10830;

    public static void main(String[] args) {
        boolean isTest = (args.length != 0) && (args[0].equals("test"));
        log.info("isTest: {}", isTest);
        SingletonContainer.init(isTest);

        QueryHandlerMap queryHandlerMap = new ShardServerQueryHandlerMap().configureMappings();
        QueryDispatcher queryDispatcher = new QueryDispatcher(queryHandlerMap);
        new ServerRunner(PORT, queryDispatcher).run();
    }

}
