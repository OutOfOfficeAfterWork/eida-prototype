package org.outofoffice.eida.common.io;

import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
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

    public static void appendLineToFile(String filePath, String... columns) throws FileNotFoundException {
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) throw new FileNotFoundException(filePath);

        try {
            String line = String.join(", ", columns) + "\n";
            Files.write(path, line.getBytes(UTF_8), APPEND);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void deleteFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) throw new FileNotFoundException(filePath);

        boolean success = file.delete();
        if (!success) throw new IllegalStateException("파일 삭제에 실패했습니다.");
    }


    public static Map<String, String> readFileAsMap(String filePath) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            Map<String, String> map = new HashMap<>();
            while (reader.ready()) {
                String[] row = reader.readLine().split(", ", 2);
                String entityId = row[0];
                String shardId = row[1];
                map.put(entityId, shardId);
            }
            return map;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
