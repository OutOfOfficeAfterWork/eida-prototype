package org.outofoffice.eida.manager.core.io;

import org.outofoffice.eida.manager.exception.TableNotFoundException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileFacade {

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

    private static Map<String, String> readFileAsMap(String filePath) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Map<String, String> map = new HashMap<>();
            while (reader.ready()) {
                String[] row = reader.readLine().split(", ", 2);
                String entityId = row[0];
                String shardId = row[1];
                map.put(entityId, shardId);
            }
            return map;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
