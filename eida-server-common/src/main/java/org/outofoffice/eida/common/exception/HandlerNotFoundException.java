package org.outofoffice.eida.common.exception;

public class HandlerNotFoundException extends EidaBadRequestException {
    public HandlerNotFoundException(String command) {
        super("Handler not found to handle " + command);
    }
}
