package org.outofoffice.eida.common.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class EidaTestFileFacade extends EidaFileFacade {

    private final Map<String, List<String>> pathFileMap = new HashMap<>();


    @Override
    public List<String> getAllLines(String filePath) {
        if (!pathFileMap.containsKey(filePath)) throw new EidaFileNotFoundException(filePath);
        return pathFileMap.get(filePath);
    }

    @Override
    public void create(String filePath) {
        if (pathFileMap.containsKey(filePath)) throw new EidaDuplicatedFileException(filePath);
        pathFileMap.put(filePath, new ArrayList<>());
    }

    @Override
    public void move(String currentFilePath, String nextFilePath) {
        if (pathFileMap.containsKey(nextFilePath)) throw new EidaDuplicatedFileException(nextFilePath);
        pathFileMap.put(nextFilePath, pathFileMap.get(currentFilePath));
        pathFileMap.remove(currentFilePath);
    }

    @Override
    public void delete(String filePath) {
        if (!pathFileMap.containsKey(filePath)) throw new EidaFileNotFoundException(filePath);
        pathFileMap.remove(filePath);
    }

    @Override
    public void update(String filePath, List<String> updatedLines) {
        if (!pathFileMap.containsKey(filePath)) throw new EidaFileNotFoundException(filePath);
        pathFileMap.put(filePath, updatedLines);
    }

    @Override
    public void appendLine(String filePath, String line) {
        if (!pathFileMap.containsKey(filePath)) throw new EidaFileNotFoundException(filePath);
        pathFileMap.get(filePath).add(line);
    }

    @Override
    public List<String> listFileNames(String directoryPath) {
        return pathFileMap.keySet().stream()
            .filter(path -> path.startsWith(directoryPath))
            .map(p -> p.split(directoryPath + "/")[1])
            .collect(Collectors.toList());
    }

    @Override
    public boolean exists(String filePath) {
        return pathFileMap.containsKey(filePath);
    }
}
