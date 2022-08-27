package org.outofoffice.eida.shard.configuration;

import lombok.NoArgsConstructor;
import org.outofoffice.eida.common.io.EidaFileFacade;
import org.outofoffice.eida.common.table.TableService;
import org.outofoffice.eida.shard.controller.DdlController;
import org.outofoffice.eida.shard.controller.DmlController;
import org.outofoffice.eida.shard.service.DdlService;
import org.outofoffice.eida.shard.service.DmlService;

import static lombok.AccessLevel.PRIVATE;
import static org.outofoffice.eida.shard.configuration.ConfigConstant.SHARD_TABLE_DIR;


@NoArgsConstructor(access = PRIVATE)
public class SingletonContainer {

    public static EidaFileFacade EIDA_FILE_FACADE;
    public static TableService TABLE_SERVICE;

    public static DmlService DML_SERVICE;
    public static DmlController DML_CONTROLLER;

    public static DdlService DDL_SERVICE;
    public static DdlController DDL_CONTROLLER;


    public static void init() {
        EIDA_FILE_FACADE = new EidaFileFacade();
        TABLE_SERVICE = new TableService(SHARD_TABLE_DIR, EIDA_FILE_FACADE);

        DML_SERVICE = new DmlService(TABLE_SERVICE);
        DML_CONTROLLER = new DmlController(DML_SERVICE);

        DDL_SERVICE = new DdlService(TABLE_SERVICE);
        DDL_CONTROLLER = new DdlController(DDL_SERVICE);
    }

}
