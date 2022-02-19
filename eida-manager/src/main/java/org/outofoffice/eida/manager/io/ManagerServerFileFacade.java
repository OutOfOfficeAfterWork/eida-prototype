package org.outofoffice.eida.manager.io;

import org.outofoffice.eida.common.exception.TableNotFoundException;
import org.outofoffice.eida.common.io.EidaFileFacade;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Map;

import static org.outofoffice.eida.common.io.EidaFileFacade.readFileAsMap;
import static org.outofoffice.eida.manager.io.FilePaths.META_PATH;
import static org.outofoffice.eida.manager.io.FilePaths.TABLE_PATH;


public class ManagerServerFileFacade {

    public static void createTableFile(String tableName) {
        String filePath = TABLE_PATH + tableName;
        String[] columns = new String[]{"entityId", "shardId"};
        try {
            EidaFileFacade.createFile(filePath, columns);
        } catch (FileAlreadyExistsException e) {
            String targetFilePath = e.getMessage();
            // fixme
            throw new TableNotFoundException(targetFilePath);
        } catch (IOException e) {
            System.out.println("ioe");

        }
    }

    public static void appendLineToTableFile(String tableName, String entityId, String shardId) {
        String filePath = TABLE_PATH + tableName;
        String[] columns = new String[]{entityId, shardId};
        try {
            EidaFileFacade.appendLineToFile(filePath, columns);
        } catch (FileNotFoundException e) {
            String targetFilePath = e.getMessage();
            throw new TableNotFoundException(targetFilePath);
        }
    }

    public static void deleteTableFile(String tableName) {
        String filePath = TABLE_PATH + tableName;
        try {
            EidaFileFacade.deleteFile(filePath);
        } catch (FileNotFoundException e) {
            String targetFilePath = e.getMessage();
            throw new TableNotFoundException(targetFilePath);
        }
    }


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
