package org.outofoffice.eidaprototype.lib.exception;


import org.outofoffice.eidaprototype.lib.core.ui.EidaEntity;

public class EidaException extends RuntimeException {

    public EidaException(String message) {
        super(message);
    }

    public EidaException(EidaEntity entity) {
        super(entity.getTableName());
    }

    public EidaException(Exception e) {
        super(e.getClass().getSimpleName() + ": " + e.getMessage());
        e.printStackTrace();
    }

}
