package org.outofoffice.eida.shard.configuration;

import lombok.NoArgsConstructor;
import org.outofoffice.eida.common.table.*;
import org.outofoffice.eida.shard.controller.DdlController;
import org.outofoffice.eida.shard.controller.DmlController;
import org.outofoffice.eida.shard.service.DdlService;
import org.outofoffice.eida.shard.service.DmlService;

import static lombok.AccessLevel.PRIVATE;
import static org.outofoffice.eida.shard.configuration.ConfigConstant.*;


@NoArgsConstructor(access = PRIVATE)
public class SingletonContainer {

    public static TableRepository TABLE_REPOSITORY;

    public static TableService TABLE_SERVICE;

    public static DmlService DML_SERVICE;
    public static DmlController DML_CONTROLLER;

    public static DdlService DDL_SERVICE;
    public static DdlController DDL_CONTROLLER;


    public static void init(boolean isTest) {
        TABLE_REPOSITORY = isTest ? new TableMapRepository() : new TableFileRepository(new TableFileFacade(SHARD_TABLE_DIR));
        if (isTest) setTestData();

        TABLE_SERVICE = new TableService(TABLE_REPOSITORY);

        DML_SERVICE = new DmlService(TABLE_SERVICE);
        DML_CONTROLLER = new DmlController(DML_SERVICE);

        DDL_SERVICE = new DdlService(TABLE_SERVICE);
        DDL_CONTROLLER = new DdlController(DDL_SERVICE);
    }

    private static void setTestData() {
        Table userTable = new Table("user");
        TABLE_REPOSITORY.save(userTable);
    }

}
