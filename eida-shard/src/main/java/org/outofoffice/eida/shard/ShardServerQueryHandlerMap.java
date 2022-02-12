package org.outofoffice.eida.shard;

import org.outofoffice.eida.shard.controller.DmlController;
import org.outofoffice.eida.shard.controller.DdlController;
import org.outofoffice.eida.common.QueryHandlerMap;


public class ShardServerQueryHandlerMap extends QueryHandlerMap {

    @Override
    public QueryHandlerMap configureMappings() {
        final DdlController ddlController = DdlController.INSTANCE;
        final DmlController dmlController = DmlController.INSTANCE;

        // ddl
        // mappings.put("get all", new GetAllQueryHandler(ddlController));
        // mappings.put("get dst", new GetDestQueryHandler(ddlController));
        // mappings.put("get src", new GetSrcQueryHandler(ddlController));
        // mappings.put("report insert", new ReportInsertQueryHandler(ddlController));
        // mappings.put("report delete", new ReportDeleteQueryHandler(ddlController));

        // dml
        //mappings.put("select all", new SelectAllQueryHandler(dmlController));
        //mappings.put("select", new SelectQueryHandler(dmlController));
        //mappings.put("insert", new InsertQueryHandler(dmlController));
        //mappings.put("update", new UpdateQueryHandler(dmlController));
        //mappings.put("delete", new DeleteQueryHandler(dmlController));

        return this;
    }

}
