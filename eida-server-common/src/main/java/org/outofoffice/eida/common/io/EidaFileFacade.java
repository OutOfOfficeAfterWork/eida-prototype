package org.outofoffice.eida.common.io;


import java.io.IOException;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;


public class EidaFileFacade {

    private static final String DELIMITER = ",";

//
//    public static void createFile(String filePath, String... columns) throws IOException {
//        Path path = Paths.get(filePath);
//        Path file = Files.createFile(path);
//
//        String header = String.join(DELIMITER, columns) + "\n";
//        Files.write(file, header.getBytes(UTF_8), CREATE);
//    }
//
//    public static void appendLineToFile(String filePath, String... columns) throws IOException {
//        Path path = Paths.get(filePath);
//        String line = String.join(DELIMITER, columns) + "\n";
//        Files.write(path, line.getBytes(UTF_8), APPEND);
//    }
//
//    public static void deleteFile(String filePath) throws IOException {
//        Path path = Paths.get(filePath);
//        Files.delete(path);
//    }
//
//
//    public static Map<String, String> readFileAsMap(String filePath) throws IOException {
//        Path path = Paths.get(filePath);
//        List<String> lines = Files.readAllLines(path);
//
//        Map<String, String> map = new HashMap<>();
//        int size = lines.size();
//        for (int i = 1; i < size; i++) {
//            String line = lines.get(i);
//            int idx = line.indexOf(DELIMITER);
//            String key = line.substring(0, idx);
//            map.put(key, line);
//        }
//        return map;
//    }

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
    }

    public void appendLine(String filePath, String line) {
    }

    public List<String> listFileNames(String directoryPath) {
        return Collections.emptyList();
    }

    public boolean exists(String filePath) {
        return false;
    }
}
