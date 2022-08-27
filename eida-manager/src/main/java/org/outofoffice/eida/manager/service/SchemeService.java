package org.outofoffice.eida.manager.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.exception.EidaBadRequestException;
import org.outofoffice.eida.common.io.EidaFileFacade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;
import static org.outofoffice.eida.common.util.StringUtils.combinePath;

@RequiredArgsConstructor
public class SchemeService {
    private final String directoryPath;
    private final EidaFileFacade eidaFileFacade;

    public void save(String tableName, String scheme) {
        String filePath = combinePath(directoryPath, tableName);

        eidaFileFacade.create(filePath);
        eidaFileFacade.appendLine(filePath, scheme);
    }

    public String findByName(String tableName) {
        String filePath = combinePath(directoryPath, tableName);

        List<String> allLines = eidaFileFacade.getAllLines(filePath);
        return allLines.stream().findFirst().orElseThrow();
    }

    public void rename(String currentName, String nextName) {
        String currentFilePath = combinePath(directoryPath, currentName);
        String nextFilePath = combinePath(directoryPath, nextName);

        eidaFileFacade.move(currentFilePath, nextFilePath);
    }

    public void delete(String tableName) {
        String filePath = combinePath(directoryPath, tableName);
        eidaFileFacade.delete(filePath);
    }

    public void createColumn(String tableName, String columnName) {
        String scheme = findByName(tableName);
        Set<String> columnNames = Arrays.stream(scheme.split(",")).collect(toSet());
        if (columnNames.contains(columnName)) {
            throw new EidaBadRequestException("duplicated column name");
        }

        String filePath = combinePath(directoryPath, tableName);
        String fixedScheme = scheme + "," + columnName;
        eidaFileFacade.update(filePath, singletonList(fixedScheme));
    }

    public void renameColumn(String tableName, String currentColumn, String nextColumn) {
        String scheme = findByName(tableName);
        Set<String> columnNames = Arrays.stream(scheme.split(",")).collect(toSet());
        if (columnNames.contains(nextColumn)) {
            throw new EidaBadRequestException("duplicated column name");
        }

        String filePath = combinePath(directoryPath, tableName);
        String fixedScheme = Arrays.stream(scheme.split(","))
            .map(column -> column.equals(currentColumn) ? nextColumn : column)
            .collect(joining(","));
        eidaFileFacade.update(filePath, singletonList(fixedScheme));
    }

    public int deleteColumn(String tableName, String columnName) {
        String scheme = findByName(tableName);
        List<String> columns = new ArrayList<>(Arrays.asList(scheme.split(",")));
        int idx = columns.indexOf(columnName);
        if (idx == 0) {
            throw new EidaBadRequestException("cannot remove primary key");
        }
        columns.remove(idx);

        String filePath = combinePath(directoryPath, tableName);
        String fixedScheme = String.join(",", columns);
        eidaFileFacade.update(filePath, singletonList(fixedScheme));
        return idx;
    }
}
