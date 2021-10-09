package org.outofoffice.eidaprototype.lib.core;


public class EidaException extends RuntimeException {

    public EidaException(String message) {
        super(message);
    }

    public EidaException(Exception e) {
        super(e.getClass().getSimpleName() + ": " + e.getMessage());
    }

}
