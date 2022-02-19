package org.outofoffice.eida.common.exception;

public class RowNotFoundException extends EidaBadRequestException {
    public RowNotFoundException(String tableName) {
        super("Row not found in " + tableName);
    }
}
