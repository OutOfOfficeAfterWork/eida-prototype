package org.outofoffice.eidaprototype.lib.core.query;


public class EidaDmlGenerator {

    public String createSelectAllQuery(String tableName) {
        return "select " + tableName;
    }

    public <ID> String createSelectByIdQuery(String tableName, ID id) {
        return "select " + tableName + " " + id;
    }

    public String createInsertQuery(String tableName, String serialized) {
        return "insert " + tableName + " " + serialized;
    }

}
