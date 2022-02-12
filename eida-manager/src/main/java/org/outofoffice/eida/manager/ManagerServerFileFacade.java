package org.outofoffice.eida.manager;

import org.outofoffice.eida.common.exception.TableNotFoundException;

import java.io.FileNotFoundException;
import java.util.Map;

import static org.outofoffice.eida.common.io.FileFacade.readFileAsMap;

public class ManagerServerFileFacade {

    //private static final String FILE_ROOT = "/";
    private static final String FILE_ROOT = "file-root/";
    private static final String MANAGER_PATH = FILE_ROOT + "eida-manager/";
    private static final String TABLE_PATH = MANAGER_PATH + "table/";
    private static final String META_PATH = MANAGER_PATH + "meta/";

    public static Map<String, String> readTableFile(String tableName) {
        String filePath = TABLE_PATH + tableName;
        try {
            return readFileAsMap(filePath);
        } catch (FileNotFoundException e) {
            String targetFilePath = e.getMessage();
            throw new TableNotFoundException(targetFilePath);
        }
    }

    public static Map<String, String> readShardFile() {
        String filePath = META_PATH + "shard";
        try {
            return readFileAsMap(filePath);
        } catch (FileNotFoundException e) {
            String targetFilePath = e.getMessage();
            throw new IllegalStateException("shard file not found in " + targetFilePath);
        }
    }

}
