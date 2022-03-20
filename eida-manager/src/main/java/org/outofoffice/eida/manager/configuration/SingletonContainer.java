package org.outofoffice.eida.manager.configuration;

import lombok.NoArgsConstructor;
import org.outofoffice.eida.manager.controller.DllController;
import org.outofoffice.eida.manager.repository.MetadataFileRepository;
import org.outofoffice.eida.manager.repository.MetadataRepository;
import org.outofoffice.eida.manager.repository.TableFileRepository;
import org.outofoffice.eida.manager.repository.TableRepository;
import org.outofoffice.eida.manager.service.DllService;
import org.outofoffice.eida.manager.service.Partitioner;

import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class SingletonContainer {

    public static final TableRepository TABLE_REPOSITORY;
    public static final MetadataRepository METADATA_REPOSITORY;

    public static final Partitioner PARTITIONER;

    public static final DllService DLL_SERVICE;
    public static final DllController DLL_CONTROLLER;

    static {
        TABLE_REPOSITORY = new TableFileRepository();
        METADATA_REPOSITORY = new MetadataFileRepository();

        PARTITIONER = new Partitioner(TABLE_REPOSITORY, METADATA_REPOSITORY);
        PARTITIONER.init();

        DLL_SERVICE = new DllService(TABLE_REPOSITORY, METADATA_REPOSITORY, PARTITIONER);
        DLL_CONTROLLER = new DllController(DLL_SERVICE);
    }

}
