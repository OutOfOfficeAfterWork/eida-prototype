package org.outofoffice.eida.shard;

import org.outofoffice.eida.common.QueryHandlerMap;
import org.outofoffice.eida.shard.controller.DmlController;
import org.outofoffice.eida.shard.handler.dml.*;


public class ShardServerQueryHandlerMap extends QueryHandlerMap {

    @Override
    public QueryHandlerMap configureMappings() {
        final DmlController dmlController = DmlController.INSTANCE;

        // dml
        mappings.put("select all", new SelectAllQueryHandler(dmlController));
        mappings.put("select", new SelectQueryHandler(dmlController));
        mappings.put("insert", new InsertQueryHandler(dmlController));
        mappings.put("update", new UpdateQueryHandler(dmlController));
        mappings.put("delete", new DeleteQueryHandler(dmlController));

        return this;
    }

}
