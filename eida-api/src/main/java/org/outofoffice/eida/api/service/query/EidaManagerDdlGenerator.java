package org.outofoffice.eida.api.service.query;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EidaManagerDdlGenerator {

    public String createCreateTableQuery(String tableName, List<String> columnNames) {
        return "create table, " + tableName + " " + String.join(",", columnNames);
    }

    public String createRenameTableQuery(String currentName, String nextName) {
        return "rename table, " + currentName + " " + nextName;
    }

    public String createDropTableQuery(String tableName) {
        return "drop table, " + tableName;
    }

}
