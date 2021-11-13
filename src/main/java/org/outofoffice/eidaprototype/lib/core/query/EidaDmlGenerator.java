package org.outofoffice.eidaprototype.lib.core.query;


public class EidaDmlGenerator {

    public String createSelectAllQuery(String tableName) {
        return "dml query";
    }

    public <ID> String createSelectByIdQuery(String shardUrl, String tableName, ID id) {
        if (id.toString().equals("1")) {
            return "dml query1";
        }
        return "dml query2";
    }

    public String createInsertQuery(String shardUrl, String tableName, String serialized) {
        return "dml query";
    }

}
