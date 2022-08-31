package org.outofoffice.eida.common.io;


import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.util.stream.Collectors.toList;


public class EidaFileFacade {

    public List<String> getAllLines(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllLines(path);
        } catch (NoSuchFileException e) {
            throw new EidaFileNotFoundException(filePath);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void create(String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            throw new EidaDuplicatedFileException(filePath);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void move(String currentFilePath, String nextFilePath) {
        try {
            Path src = Paths.get(currentFilePath);
            Path dst = Paths.get(nextFilePath);
            Files.move(src, dst);
        } catch (FileAlreadyExistsException e) {
            throw new EidaDuplicatedFileException(nextFilePath);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void delete(String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.delete(path);
        } catch (NoSuchFileException e) {
            throw new EidaFileNotFoundException(filePath);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void update(String filePath, List<String> updatedLines) {
        try {
            Path path = Paths.get(filePath);
            Files.write(path, updatedLines);
        } catch (NoSuchFileException e) {
            throw new EidaFileNotFoundException(filePath);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void appendLine(String filePath, String line) {
        try {
            Path path = Paths.get(filePath);
            Files.write(path, List.of(line), APPEND);
        } catch (NoSuchFileException e) {
            throw new EidaFileNotFoundException(filePath);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<String> listFileNames(String directoryPath) {
        try {
            return Files.list(Paths.get(directoryPath))
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(toList());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean exists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

}
