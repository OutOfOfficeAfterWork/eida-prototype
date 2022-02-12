package org.outofoffice.eida.manager;

import org.outofoffice.eida.common.QueryHandlerMap;
import org.outofoffice.eida.manager.controller.DllController;
import org.outofoffice.eida.manager.controller.DmlController;
import org.outofoffice.eida.manager.handler.dll.*;
import org.outofoffice.eida.manager.handler.dml.*;


public class ManagerServerQueryHandlerMap extends QueryHandlerMap {

    @Override
    public QueryHandlerMap configureMappings() {
        final DllController dllController = DllController.INSTANCE;
        final DmlController dmlController = DmlController.INSTANCE;

        // dll
        mappings.put("get all", new GetAllQueryHandler(dllController));
        mappings.put("get dst", new GetDestQueryHandler(dllController));
        mappings.put("get src", new GetSrcQueryHandler(dllController));
        mappings.put("report insert", new ReportInsertQueryHandler(dllController));
        mappings.put("report delete", new ReportDeleteQueryHandler(dllController));

        // dml
        mappings.put("select all", new SelectAllQueryHandler(dmlController));
        mappings.put("select", new SelectQueryHandler(dmlController));
        mappings.put("insert", new InsertQueryHandler(dmlController));
        mappings.put("update", new UpdateQueryHandler(dmlController));
        mappings.put("delete", new DeleteQueryHandler(dmlController));

        return this;
    }

}
