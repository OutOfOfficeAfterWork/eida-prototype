package org.outofoffice.eida.common.exception;

public class TableNotFoundException extends EidaBadRequestException {
    public TableNotFoundException(Exception e) {
        super(e.getClass().getSimpleName() + ": " + e.getMessage());
    }
}
