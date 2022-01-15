package org.outofoffice.eida.manager.core;

import org.outofoffice.eida.manager.exception.ExceptionHandler;
import org.outofoffice.eida.manager.exception.HandlerNotFoundException;
import org.outofoffice.eida.manager.exception.HandlerNotFoundExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandlerMappings {
    private final Map<Class<? extends Exception>, ExceptionHandler> mappings = new HashMap<>();

    public ExceptionHandlerMappings() {
        mappings.put(HandlerNotFoundException.class, new HandlerNotFoundExceptionHandler());
    }

    public ExceptionHandler getOrDefault(Class<? extends Exception> exceptionClass) {
        return mappings.getOrDefault(exceptionClass, (e) -> "");
    }

}
