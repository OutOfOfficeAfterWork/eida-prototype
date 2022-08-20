package org.outofoffice.eida.manager.configuration.handler;

import org.outofoffice.eida.common.QueryHandlerMap;
import org.outofoffice.eida.manager.configuration.handler.ddl.*;
import org.outofoffice.eida.manager.configuration.handler.dll.*;
import org.outofoffice.eida.manager.controller.DdlController;
import org.outofoffice.eida.manager.controller.DllController;
import org.outofoffice.eida.manager.controller.SequenceController;

import static org.outofoffice.eida.manager.configuration.SingletonContainer.*;


public class ManagerServerQueryHandlerMap extends QueryHandlerMap {

    @Override
    public QueryHandlerMap configureMappings() {

        DllController dllController = DLL_CONTROLLER;
        mappings.put("add shard", new AddShardQueryHandler(dllController));
        mappings.put("get all", new GetAllQueryHandler(dllController));
        mappings.put("get dst", new GetDestQueryHandler(dllController));
        mappings.put("get src", new GetSrcQueryHandler(dllController));
        mappings.put("report insert", new ReportInsertQueryHandler(dllController));
        mappings.put("report delete", new ReportDeleteQueryHandler(dllController));

        DdlController ddlController = DDL_CONTROLLER;
        mappings.put("create table", new CreateTableQueryHandler(ddlController));
        mappings.put("rename table", new RenameTableQueryHandler(ddlController));
        mappings.put("drop table", new DropTableQueryHandler(ddlController));
        mappings.put("get all table", new GetAllTableQueryHandler(ddlController));
        mappings.put("create column", new CreateColumnQueryHandler(ddlController));
        mappings.put("rename column", new RenameColumnQueryHandler(ddlController));
        mappings.put("delete column", new DeleteColumnQueryHandler(ddlController));
        mappings.put("get scheme", new GetSchemeQueryHandler(ddlController));

        SequenceController sequenceController = SEQUENCE_CONTROLLER;
        mappings.put("nextval", new NextValQueryHandler(sequenceController));

        return this;
    }

}
