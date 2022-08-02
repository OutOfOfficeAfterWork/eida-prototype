package org.outofoffice.eida.manager.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.exception.EidaBadRequestException;
import org.outofoffice.eida.manager.repository.SchemeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
public class SchemeService {
    private final SchemeRepository schemeRepository;

    public void save(String tableName, String scheme) {
        schemeRepository.save(tableName, scheme);
    }

    public String findByName(String tableName) {
        return schemeRepository.findByName(tableName);
    }

    public void rename(String currentName, String nextName) {
        schemeRepository.rename(currentName, nextName);
    }

    public void delete(String tableName) {
        schemeRepository.delete(tableName);
    }

    public void createColumn(String tableName, String columnName) {
        String scheme = schemeRepository.findByName(tableName);
        Set<String> columnNames = Arrays.stream(scheme.split(",")).collect(toSet());
        if (columnNames.contains(columnName)) {
            throw new EidaBadRequestException("duplicated column name");
        }
        String fixedScheme = scheme + "," + columnName;
        schemeRepository.save(tableName, fixedScheme);
    }

    public void renameColumn(String tableName, String currentColumn, String nextColumn) {
        String scheme = schemeRepository.findByName(tableName);
        Set<String> columnNames = Arrays.stream(scheme.split(",")).collect(toSet());
        if (columnNames.contains(nextColumn)) {
            throw new EidaBadRequestException("duplicated column name");
        }
        String fixedScheme = Arrays.stream(scheme.split(","))
            .map(column -> column.equals(currentColumn) ? nextColumn : column)
            .collect(joining(","));
        schemeRepository.save(tableName, fixedScheme);
    }

    public int deleteColumn(String tableName, String columnName) {
        String scheme = schemeRepository.findByName(tableName);
        List<String> columns = Arrays.asList(scheme.split(","));
        int idx = columns.indexOf(columnName);
        if (idx == 0) {
            throw new EidaBadRequestException("cannot remove primary key");
        }

        columns.remove(idx);
        String fixedScheme = String.join(",", columns);
        schemeRepository.save(tableName, fixedScheme);
        return idx;
    }
}
