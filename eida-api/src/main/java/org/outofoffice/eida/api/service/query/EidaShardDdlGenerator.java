package org.outofoffice.eida.api.service.query;

import org.springframework.stereotype.Component;

@Component
public class EidaShardDdlGenerator {

    public String createCreateTableQuery(String tableName) {
        return "create table, " + tableName;
    }

    public String createRenameTableQuery(String currentName, String nextName) {
        return "rename table, " + currentName + " " + nextName;
    }

    public String createDropTableQuery(String tableName) {
        return "drop table, " + tableName;
    }

    public String createCreateColumnQuery(String tableName, String defaultValue) {
        String value = (defaultValue == null)
            ? ""
            : " " + defaultValue;
        return "create column, " + tableName + value;
    }

    public String createDeleteColumnQuery(String tableName, int columnIndex) {
        return "delete column, " + tableName + " " + columnIndex;
    }
}
