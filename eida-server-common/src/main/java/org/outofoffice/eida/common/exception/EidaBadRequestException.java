package org.outofoffice.eida.common.exception;

public class EidaBadRequestException extends RuntimeException {
    public EidaBadRequestException(String message) {
        super(message);
    }

    public EidaBadRequestException(Exception e) {
        super(e.getClass().getSimpleName() + ": " + e.getMessage());
    }
}
