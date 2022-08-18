package org.outofoffice.eida.manager.configuration.handler;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.QueryHandler;
import org.outofoffice.eida.manager.controller.SequenceController;

@RequiredArgsConstructor
public class NextValQueryHandler implements QueryHandler {
    private final SequenceController sequenceController;

    @Override
    public String handle(String parameter) {
        long val = sequenceController.nextVal();
        return String.valueOf(val);
    }
}
