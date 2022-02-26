package org.outofoffice.eida.manager.io;

import org.outofoffice.eida.common.exception.EidaBadRequestException;
import org.outofoffice.eida.common.exception.TableNotFoundException;
import org.outofoffice.eida.common.io.EidaFileFacade;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
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
            throw new EidaBadRequestException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void appendLineToTableFile(String tableName, String entityId, String shardId) {
        String filePath = TABLE_PATH + tableName;
        String[] columns = new String[]{entityId, shardId};
        try {
            EidaFileFacade.appendLineToFile(filePath, columns);
        } catch (NoSuchFileException e) {
            throw new TableNotFoundException(e);
        } catch (IOException e){
            throw new IllegalStateException(e);
        }
    }

    public static void deleteTableFile(String tableName) {
        String filePath = TABLE_PATH + tableName;
        try {
            EidaFileFacade.deleteFile(filePath);
        } catch (NoSuchFileException e) {
            throw new TableNotFoundException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    public static Map<String, String> readTableFile(String tableName) {
        String filePath = TABLE_PATH + tableName;
        try {
            return readFileAsMap(filePath);
        } catch (NoSuchFileException e) {
            throw new TableNotFoundException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Map<String, String> readShardFile() {
        String filePath = META_PATH + "shard";
        try {
            return readFileAsMap(filePath);
        } catch (NoSuchFileException e) {
            throw new TableNotFoundException(e);
        } catch (IOException e){
            throw new IllegalStateException(e);
        }
    }
}
