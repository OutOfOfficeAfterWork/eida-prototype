package org.outofoffice.eida.manager.configuration;

import lombok.NoArgsConstructor;
import org.outofoffice.eida.common.table.*;
import org.outofoffice.eida.manager.domain.ShardMapping;
import org.outofoffice.eida.manager.infrastructure.*;
import org.outofoffice.eida.manager.repository.SchemeRepository;
import org.outofoffice.eida.manager.repository.ShardMappingRepository;
import org.outofoffice.eida.manager.service.SchemeService;
import org.outofoffice.eida.manager.service.ShardMappingService;
import org.outofoffice.eida.manager.controller.DllController;
import org.outofoffice.eida.manager.service.DllService;
import org.outofoffice.eida.manager.service.Partitioner;

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
    }

    private static void setTestData() {
        Table userTable = new Table("user");
        userTable.appendRow("1", "1");
        userTable.appendRow("2", "1");
        userTable.appendRow("3", "2");
        TABLE_REPOSITORY.save(userTable);

        ShardMapping shardMapping = new ShardMapping();
        shardMapping.appendRow("1", "localhost:10830");
        shardMapping.appendRow("2", "localhost:10831");
        shardMapping.appendRow("3", "localhost:10832");
        SHARD_MAPPING_REPOSITORY.save(shardMapping);

        String scheme = "id,value";
        SCHEME_REPOSITORY.save("user", scheme);
    }

}
