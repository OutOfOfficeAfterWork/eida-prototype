package org.outofoffice.eida.manager.configuration;

import org.outofoffice.eida.manager.controller.DllController;
import org.outofoffice.eida.manager.repository.MetadataFileRepository;
import org.outofoffice.eida.manager.repository.MetadataRepository;
import org.outofoffice.eida.manager.repository.TableFileRepository;
import org.outofoffice.eida.manager.repository.TableRepository;
import org.outofoffice.eida.manager.service.DllService;


public class SingletonContainer {

    public static final TableRepository TABLE_REPOSITORY = new TableFileRepository();
    public static final MetadataRepository METADATA_REPOSITORY = new MetadataFileRepository();

    public static final DllService DLL_SERVICE = new DllService(TABLE_REPOSITORY, METADATA_REPOSITORY);
    public static final DllController DLL_CONTROLLER = new DllController(DLL_SERVICE);

}
