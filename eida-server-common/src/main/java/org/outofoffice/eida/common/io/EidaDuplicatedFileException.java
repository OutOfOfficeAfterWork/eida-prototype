package org.outofoffice.eida.common.io;

import org.outofoffice.common.exception.EidaException;

public class EidaDuplicatedFileException extends EidaException {
    public EidaDuplicatedFileException(String filePath) {
        super(filePath);
    }
}
