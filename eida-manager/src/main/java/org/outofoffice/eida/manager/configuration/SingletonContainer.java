package org.outofoffice.eida.manager.configuration;

import lombok.NoArgsConstructor;
import org.outofoffice.eida.common.io.EidaFileFacade;
import org.outofoffice.eida.common.table.TableService;
import org.outofoffice.eida.manager.controller.DdlController;
import org.outofoffice.eida.manager.controller.DllController;
import org.outofoffice.eida.manager.controller.SequenceController;
import org.outofoffice.eida.manager.service.*;

import static lombok.AccessLevel.PRIVATE;
import static org.outofoffice.eida.manager.configuration.ConfigConstant.*;


@NoArgsConstructor(access = PRIVATE)
public class SingletonContainer {

    public static EidaFileFacade EIDA_FILE_FACADE;
    public static Partitioner PARTITIONER;

    public static TableService TABLE_SERVICE;
    public static ShardMappingService SHARD_MAPPING_SERVICE;
    public static SchemeService SCHEME_SERVICE;

    public static DllService DLL_SERVICE;
    public static DllController DLL_CONTROLLER;

    public static DdlService DDL_SERVICE;
    public static DdlController DDL_CONTROLLER;

    public static SequenceService SEQUENCE_SERVICE;
    public static SequenceController SEQUENCE_CONTROLLER;


    public static void init() {
        EIDA_FILE_FACADE = new EidaFileFacade();

        TABLE_SERVICE = new TableService(TABLE_DIR_PATH, EIDA_FILE_FACADE);
        SHARD_MAPPING_SERVICE = new ShardMappingService(SHARD_MAPPING_FILE_PATH, EIDA_FILE_FACADE);
        SCHEME_SERVICE = new SchemeService(SCHEME_DIR_PATH, EIDA_FILE_FACADE);
        SEQUENCE_SERVICE = new SequenceService(SEQUENCE_FILE_PATH, EIDA_FILE_FACADE);

        DLL_SERVICE = new DllService(TABLE_SERVICE, SHARD_MAPPING_SERVICE, SCHEME_SERVICE, PARTITIONER);
        DDL_SERVICE = new DdlService(SCHEME_SERVICE, TABLE_SERVICE, PARTITIONER);

        DLL_CONTROLLER = new DllController(DLL_SERVICE);
        DDL_CONTROLLER = new DdlController(DDL_SERVICE);

        SEQUENCE_CONTROLLER = new SequenceController(SEQUENCE_SERVICE);

        PARTITIONER = new Partitioner(TABLE_SERVICE, SHARD_MAPPING_SERVICE);
        PARTITIONER.init();
    }

}
