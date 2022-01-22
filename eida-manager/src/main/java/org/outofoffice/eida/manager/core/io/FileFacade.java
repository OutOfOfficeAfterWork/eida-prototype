package org.outofoffice.eida.manager.core.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class FileFacade {

    //private static final String FILE_ROOT = "/";
    private static final String FILE_ROOT = "file-root/";
    private static final String MANAGER_PATH = FILE_ROOT + "eida-manager/";
    private static final String TABLE_PATH = MANAGER_PATH + "table/";

    public static Map<String, String> readTableFile(String tableName){
        String filePath = TABLE_PATH + tableName;
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Map<String, String> map = new HashMap<>();
            while(reader.ready()) {
                String[] row = reader.readLine().split(", ", 2);
                String entityId = row[0];
                String shardId = row[1];
                map.put(entityId, shardId);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
