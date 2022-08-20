package org.outofoffice.eida.manager.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.infrastructure.SequenceFileFacade;


@RequiredArgsConstructor
public class SequenceService {

    private final SequenceFileFacade sequenceFileFacade;

    public long nextVal() {
        long currVal = sequenceFileFacade.find();
        long nextVal = currVal + 1;
        sequenceFileFacade.save(nextVal);
        return nextVal;
    }

}
