package org.outofoffice.eida.manager.configuration;

import lombok.NoArgsConstructor;
import org.outofoffice.eida.common.table.*;
import org.outofoffice.eida.manager.domain.ShardMapping;
import org.outofoffice.eida.manager.infrastructure.ShardMappingMockRepository;
import org.outofoffice.eida.manager.repository.ShardMappingRepository;
import org.outofoffice.eida.manager.service.ShardMappingService;
import org.outofoffice.eida.manager.controller.DllController;
import org.outofoffice.eida.manager.service.DllService;
import org.outofoffice.eida.manager.service.Partitioner;

import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class SingletonContainer {

    public static final TableRepository TABLE_REPOSITORY;
    public static final ShardMappingRepository SHARD_MAPPING_REPOSITORY;

    public static final Partitioner PARTITIONER;

    public static final TableService TABLE_SERVICE;
    public static final ShardMappingService SHARD_MAPPING_SERVICE;

    public static final DllService DLL_SERVICE;
    public static final DllController DLL_CONTROLLER;

    static {
        TABLE_REPOSITORY = new TableMapRepository();
        SHARD_MAPPING_REPOSITORY = new ShardMappingMockRepository();
        setTestData();

        PARTITIONER = new Partitioner(TABLE_REPOSITORY, SHARD_MAPPING_REPOSITORY);
        PARTITIONER.init();

        TABLE_SERVICE = new TableService(TABLE_REPOSITORY);
        SHARD_MAPPING_SERVICE = new ShardMappingService(SHARD_MAPPING_REPOSITORY);

        DLL_SERVICE = new DllService(TABLE_SERVICE, SHARD_MAPPING_SERVICE, PARTITIONER);
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
    }

}
