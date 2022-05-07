package org.outofoffice.eida.common.exception;

public class DuplicatedKeyException extends EidaBadRequestException {
    public DuplicatedKeyException(String tableName, String id) {
        super("Duplicated key in " + tableName + " & " + id);
    }
}
