package org.outofoffice.eida.manager.core.controller;

public class DmlController {

    public String createSelectAllQuery(String tableName) {
        return "select all, " + tableName;
    }

    public String createSelectByIdQuery(String tableName, String id) {
        return "select, " + tableName + " " + id;
    }

    public String createInsertQuery(String tableName, String data) {
        return "insert, " + tableName + " " + data;
    }

    public String createUpdateQuery(String tableName, String data) {
        return "update, " + tableName + " " + data;
    }

    public String createDeleteQuery(String tableName, String id) {
        return "delete, " + tableName + " " + id;
    }
}
