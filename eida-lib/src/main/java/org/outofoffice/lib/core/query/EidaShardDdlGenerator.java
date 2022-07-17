package org.outofoffice.lib.core.query;

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
}
