package org.outofoffice.eida.manager.exception;

public class TableNotFoundException extends EidaBadRequestException {
    public TableNotFoundException(String targetFilePath) {
        super("Table file not found in " + targetFilePath);
    }
}
