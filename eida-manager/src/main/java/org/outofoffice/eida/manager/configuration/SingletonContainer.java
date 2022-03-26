package org.outofoffice.eida.manager.configuration;

import lombok.NoArgsConstructor;
import org.outofoffice.eida.manager.controller.DllController;
import org.outofoffice.eida.manager.repository.MetadataFileRepository;
import org.outofoffice.eida.manager.repository.MetadataRepository;
import org.outofoffice.eida.common.table.TableFileRepository;
import org.outofoffice.eida.common.table.TableRepository;
import org.outofoffice.eida.manager.service.DllService;
import org.outofoffice.eida.manager.service.Partitioner;
import org.outofoffice.eida.common.table.TableService;

import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class SingletonContainer {

    public static final TableRepository TABLE_REPOSITORY;
    public static final MetadataRepository METADATA_REPOSITORY;

    public static final Partitioner PARTITIONER;

    public static final TableService TABLE_SERVICE;

    public static final DllService DLL_SERVICE;
    public static final DllController DLL_CONTROLLER;

    static {
        TABLE_REPOSITORY = new TableFileRepository();
        METADATA_REPOSITORY = new MetadataFileRepository();

        PARTITIONER = new Partitioner(TABLE_REPOSITORY, METADATA_REPOSITORY);
        PARTITIONER.init();

        TABLE_SERVICE = new TableService(TABLE_REPOSITORY);

        DLL_SERVICE = new DllService(TABLE_SERVICE, TABLE_REPOSITORY, METADATA_REPOSITORY, PARTITIONER);
        DLL_CONTROLLER = new DllController(DLL_SERVICE);
    }

}
