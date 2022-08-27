package org.outofoffice.eida.common.table;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.io.EidaFileFacade;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.outofoffice.eida.common.table.Table.DELIMITER;
import static org.outofoffice.eida.common.util.StringUtils.combineComma;
import static org.outofoffice.eida.common.util.StringUtils.combinePath;


@RequiredArgsConstructor
public class TableService {

    private final String directoryPath;
    private final EidaFileFacade eidaFileFacade;

    public void create(String tableName) {
        String filePath = combinePath(directoryPath, tableName);
        eidaFileFacade.create(filePath);
    }

    public void rename(String currentName, String nextName) {
        String currentFilePath = combinePath(directoryPath, currentName);
        String nextFilePath = combinePath(directoryPath, nextName);
        eidaFileFacade.move(currentFilePath, nextFilePath);
    }

    public void drop(String tableName) {
        String filePath = combinePath(directoryPath, tableName);
        eidaFileFacade.delete(filePath);
    }

    public void appendColumn(String tableName, String value) {
        String filePath = combinePath(directoryPath, tableName);
        List<String> lines = eidaFileFacade.getAllLines(filePath);
        List<String> updatedLines = lines.stream()
            .map(line -> combineComma(line, value))
            .collect(toList());
        eidaFileFacade.update(filePath, updatedLines);
    }

    public void deleteColumn(String tableName, int columnIndex) {
        String filePath = combinePath(directoryPath, tableName);
        List<String> lines = eidaFileFacade.getAllLines(filePath);
        List<String> updatedLines = lines.stream()
            .map(line -> {
                List<String> immutableList = Arrays.asList(line.split(","));
                List<String> columns = new ArrayList<>(immutableList);
                columns.remove(columnIndex);
                return String.join(",", columns);
            })
            .collect(toList());
        eidaFileFacade.update(filePath, updatedLines);
    }

    public void appendRow(String tableName, String id, String value) {
        String filePath = combinePath(directoryPath, tableName);
        String line = combineComma(id, value);
        eidaFileFacade.appendLine(filePath, line);
    }

    public void appendRow(String tableName, String row) {
        String filePath = combinePath(directoryPath, tableName);
        eidaFileFacade.appendLine(filePath, row);
    }

    public void deleteRow(String tableName, String id) {
        String filePath = combinePath(directoryPath, tableName);
        List<String> remainLines = eidaFileFacade.getAllLines(filePath).stream()
            .filter(line -> !line.split(",", 2)[0].equals(id))
            .collect(toList());
        eidaFileFacade.update(filePath, remainLines);
    }

    public Table findByName(String tableName) {
        String filePath = combinePath(directoryPath, tableName);
        List<String> lines = eidaFileFacade.getAllLines(filePath);
        Map<String, String> map = new HashMap<>();
        for (String line : lines) {
            int idx = line.indexOf(DELIMITER);
            String key = line.substring(0, idx);
            map.put(key, line);
        }
        return new Table(tableName, map);
    }

    public List<String> findAllFileNames() {
        return eidaFileFacade.listFileNames(directoryPath);
    }

    public boolean existByName(String tableName) {
        String filePath = combinePath(directoryPath, tableName);
        return eidaFileFacade.exists(filePath);
    }
}
