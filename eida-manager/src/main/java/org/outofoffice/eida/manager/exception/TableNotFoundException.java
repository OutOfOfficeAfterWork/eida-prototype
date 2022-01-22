package org.outofoffice.eida.manager.exception;

public class TableNotFoundException extends EidaBadRequestException {
    public TableNotFoundException(String tableName) {
        super("Table file not found: " + tableName);
    }
}
