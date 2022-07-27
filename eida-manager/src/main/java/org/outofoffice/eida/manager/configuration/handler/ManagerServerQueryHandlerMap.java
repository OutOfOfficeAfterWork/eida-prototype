package org.outofoffice.eida.manager.configuration.handler;

import org.outofoffice.eida.common.QueryHandlerMap;
import org.outofoffice.eida.manager.configuration.handler.ddl.CreateTableQueryHandler;
import org.outofoffice.eida.manager.configuration.handler.ddl.DropTableQueryHandler;
import org.outofoffice.eida.manager.configuration.handler.ddl.GetAllTableQueryHandler;
import org.outofoffice.eida.manager.configuration.handler.ddl.RenameTableQueryHandler;
import org.outofoffice.eida.manager.controller.DdlController;
import org.outofoffice.eida.manager.controller.DllController;
import org.outofoffice.eida.manager.configuration.handler.dll.*;

import static org.outofoffice.eida.manager.configuration.SingletonContainer.DDL_CONTROLLER;
import static org.outofoffice.eida.manager.configuration.SingletonContainer.DLL_CONTROLLER;


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

        return this;
    }

}
