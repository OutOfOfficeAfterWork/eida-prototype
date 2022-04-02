package org.outofoffice.eida.manager.configuration;

import lombok.NoArgsConstructor;
import org.outofoffice.eida.manager.infrastructure.ShardMappingFileRepository;
import org.outofoffice.eida.manager.repository.ShardMappingRepository;
import org.outofoffice.eida.manager.service.ShardMappingService;
import org.outofoffice.eida.manager.controller.DllController;
import org.outofoffice.eida.common.table.TableFileRepository;
import org.outofoffice.eida.common.table.TableRepository;
import org.outofoffice.eida.manager.service.DllService;
import org.outofoffice.eida.manager.service.Partitioner;
import org.outofoffice.eida.common.table.TableService;

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
        TABLE_REPOSITORY = new TableFileRepository();
        SHARD_MAPPING_REPOSITORY = new ShardMappingFileRepository();

        PARTITIONER = new Partitioner(TABLE_REPOSITORY, SHARD_MAPPING_REPOSITORY);
        PARTITIONER.init();

        TABLE_SERVICE = new TableService(TABLE_REPOSITORY);
        SHARD_MAPPING_SERVICE = new ShardMappingService(SHARD_MAPPING_REPOSITORY);

        DLL_SERVICE = new DllService(TABLE_SERVICE, TABLE_REPOSITORY, SHARD_MAPPING_SERVICE, SHARD_MAPPING_REPOSITORY, PARTITIONER);
        DLL_CONTROLLER = new DllController(DLL_SERVICE);
    }

}
