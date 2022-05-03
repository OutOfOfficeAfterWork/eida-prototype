package org.outofoffice.eida.shard.configuration.handler;

import org.outofoffice.eida.common.QueryHandlerMap;
import org.outofoffice.eida.shard.controller.DmlController;
import org.outofoffice.eida.shard.configuration.handler.dml.*;

import static org.outofoffice.eida.shard.configuration.SingletonContainer.DML_CONTROLLER;


public class ShardServerQueryHandlerMap extends QueryHandlerMap {

    @Override
    public QueryHandlerMap configureMappings() {
        DmlController dmlController = DML_CONTROLLER;
        mappings.put("select all", new SelectAllQueryHandler(dmlController));
        mappings.put("select", new SelectQueryHandler(dmlController));
        mappings.put("insert", new InsertQueryHandler(dmlController));
        mappings.put("update", new UpdateQueryHandler(dmlController));
        mappings.put("delete", new DeleteQueryHandler(dmlController));

        return this;
    }

}
