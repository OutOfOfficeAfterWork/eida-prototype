package org.outofoffice.eida.manager.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.io.EidaFileFacade;
import org.outofoffice.eida.manager.domain.ShardMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.outofoffice.eida.common.util.StringUtils.combineComma;

@RequiredArgsConstructor
public class ShardMappingService {

    private final String filePath;
    private final EidaFileFacade eidaFileFacade;


    public void appendRow(String shardUrl) {
        List<String> shardMappingLines = eidaFileFacade.getAllLines(filePath);

        String shardId = String.valueOf(shardMappingLines.size() + 1);
        String line = combineComma(shardId, shardUrl);

        eidaFileFacade.appendLine(filePath, line);
    }

    public ShardMapping find() {
        Map<Integer, String> content = new HashMap<>();
        List<String> shardMappingLines = eidaFileFacade.getAllLines(filePath);
        shardMappingLines.forEach(line -> {
            String[] array = line.split(",");
            int shardId = Integer.parseInt(array[0]);
            String shardUrl = array[1];
            content.put(shardId, shardUrl);
        });
        return new ShardMapping(content);
    }

}
