package org.outofoffice.eida.manager.configuration;

import lombok.NoArgsConstructor;
import org.outofoffice.eida.common.table.*;
import org.outofoffice.eida.manager.controller.DdlController;
import org.outofoffice.eida.manager.controller.SequenceController;
import org.outofoffice.eida.manager.domain.ShardMapping;
import org.outofoffice.eida.manager.infrastructure.*;
import org.outofoffice.eida.manager.repository.SchemeRepository;
import org.outofoffice.eida.manager.repository.ShardMappingRepository;
import org.outofoffice.eida.manager.service.*;
import org.outofoffice.eida.manager.controller.DllController;

import static lombok.AccessLevel.PRIVATE;
import static org.outofoffice.eida.manager.configuration.ConfigConstant.*;


@NoArgsConstructor(access = PRIVATE)
public class SingletonContainer {

    public static TableRepository TABLE_REPOSITORY;
    public static ShardMappingRepository SHARD_MAPPING_REPOSITORY;
    public static SchemeRepository SCHEME_REPOSITORY;

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


    public static void init(boolean isTest) {
        TABLE_REPOSITORY = isTest ? new TableMapRepository() : new TableFileRepository(new TableFileFacade(TABLE_DIR_PATH));
        SCHEME_REPOSITORY = isTest ? new SchemeMockRepository() : new SchemeFileRepository(new SchemeFileFacade(SCHEME_DIR_PATH));

        SHARD_MAPPING_REPOSITORY = isTest ? new ShardMappingMockRepository() : new ShardMappingFileRepository(new ShardMappingFileFacade(SHARD_MAPPING_FILE_PATH));
        if (isTest) setTestData();

        PARTITIONER = new Partitioner(TABLE_REPOSITORY, SHARD_MAPPING_REPOSITORY);
        PARTITIONER.init();

        TABLE_SERVICE = new TableService(TABLE_REPOSITORY);
        SHARD_MAPPING_SERVICE = new ShardMappingService(SHARD_MAPPING_REPOSITORY);
        SCHEME_SERVICE = new SchemeService(SCHEME_REPOSITORY);

        DLL_SERVICE = new DllService(TABLE_SERVICE, SHARD_MAPPING_SERVICE, SCHEME_SERVICE, PARTITIONER);
        DLL_CONTROLLER = new DllController(DLL_SERVICE);

        DDL_SERVICE = new DdlService(SCHEME_SERVICE, TABLE_SERVICE, PARTITIONER);
        DDL_CONTROLLER = new DdlController(DDL_SERVICE);

        SEQUENCE_SERVICE = new SequenceService(new SequenceFileFacade(SEQUENCE_FILE_PATH));
        SEQUENCE_CONTROLLER = new SequenceController(SEQUENCE_SERVICE);
    }

    private static void setTestData() {
        Table userTable = new Table("user");
        userTable.appendRow("1", "1");
        userTable.appendRow("2", "1");
        userTable.appendRow("3", "2");
        TABLE_REPOSITORY.save(userTable);

        ShardMapping shardMapping = new ShardMapping();
        shardMapping.appendRow("localhost:10830");
        shardMapping.appendRow("localhost:10831");
        shardMapping.appendRow("localhost:10832");
        SHARD_MAPPING_REPOSITORY.save(shardMapping);

        String scheme = "id,value";
        SCHEME_REPOSITORY.save("user", scheme);
    }

}
