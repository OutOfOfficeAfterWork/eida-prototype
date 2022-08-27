package org.outofoffice.eida.manager.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.io.EidaFileFacade;

import java.util.List;

import static java.util.Collections.singletonList;


@RequiredArgsConstructor
public class SequenceService {

    private final String filePath;
    private final EidaFileFacade eidaFileFacade;

    public long nextVal() {
        List<String> allLines = eidaFileFacade.getAllLines(filePath);
        long currVal = allLines.stream().findFirst().map(Long::parseLong).orElseThrow();
        long nextVal = currVal + 1;
        eidaFileFacade.update(filePath, singletonList(String.valueOf(nextVal)));
        return nextVal;
    }

}
