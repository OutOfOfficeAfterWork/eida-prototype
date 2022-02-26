package org.outofoffice.eida.common.io;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class EidaFileFacade {

    public static void createFile(String filePath, String... columns) throws IOException {
        Path path = Paths.get(filePath);
        Path file = Files.createFile(path);

        String header = String.join(", ", columns) + "\n";
        Files.write(file, header.getBytes(UTF_8), CREATE);
    }

    public static void appendLineToFile(String filePath, String... columns) throws IOException {
        Path path = Paths.get(filePath);
        String line = String.join(", ", columns) + "\n";
        Files.write(path, line.getBytes(UTF_8), APPEND);
    }

    public static void deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.delete(path);
    }


    public static Map<String, String> readFileAsMap(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);

        Map<String, String> map = new HashMap<>();
        int size = lines.size();
        for (int i = 1; i < size; i++) {
            String line = lines.get(i);
            String[] row = line.split(", ", 2);
            String entityId = row[0];
            String shardId = row[1];
            map.put(entityId, shardId);
        }
        return map;
    }

}
