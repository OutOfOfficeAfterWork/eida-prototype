package org.outofoffice.eida.manager.exception;

public class HandlerNotFoundExceptionHandler implements ExceptionHandler {
    @Override
    public String handle(Exception e) {
        return "err: "+e.getMessage();
    }
}
