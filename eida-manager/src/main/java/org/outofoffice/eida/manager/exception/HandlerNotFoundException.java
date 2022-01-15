package org.outofoffice.eida.manager.exception;

public class HandlerNotFoundException extends RuntimeException{
    public HandlerNotFoundException(String command){
        super("Handler not found to handle " + command);
    }
}
