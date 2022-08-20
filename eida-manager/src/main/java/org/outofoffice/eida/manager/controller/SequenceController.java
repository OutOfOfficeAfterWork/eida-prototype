package org.outofoffice.eida.manager.controller;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.service.SequenceService;


@RequiredArgsConstructor
public class SequenceController {
    private final SequenceService sequenceService;

    public long nextVal() {
        return sequenceService.nextVal();
    }
}
