package org.outofoffice.eida.manager;

import org.outofoffice.eida.common.QueryHandlerMap;
import org.outofoffice.eida.manager.controller.DllController;
import org.outofoffice.eida.manager.handler.dll.*;

import static org.outofoffice.eida.manager.configuration.SingletonContainer.DLL_CONTROLLER;


public class ManagerServerQueryHandlerMap extends QueryHandlerMap {

    @Override
    public QueryHandlerMap configureMappings() {

        DllController dllController = DLL_CONTROLLER;
        mappings.put("get all", new GetAllQueryHandler(dllController));
        mappings.put("get dst", new GetDestQueryHandler(dllController));
        mappings.put("get src", new GetSrcQueryHandler(dllController));
        mappings.put("report insert", new ReportInsertQueryHandler(dllController));
        mappings.put("report delete", new ReportDeleteQueryHandler(dllController));

        return this;
    }

}
