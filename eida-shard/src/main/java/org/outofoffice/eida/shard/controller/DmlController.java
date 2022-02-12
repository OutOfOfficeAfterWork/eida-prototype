package org.outofoffice.eida.shard.controller;

public class DmlController {
    public static final DmlController INSTANCE = new DmlController();

    public String selectAll(String tableName) {
        return "select all, " + tableName;
    }

    public String selectById(String tableName, String id) {
        return "select, " + tableName + " " + id;
    }

    public void insert(String tableName, String data) {
        // return "insert, " + tableName + " " + data;
    }

    public void update(String tableName, String data) {
        // return "update, " + tableName + " " + data;
    }

    public void delete(String tableName, String id) {
        // return "delete, " + tableName + " " + id;
    }
}
