package org.outofoffice.eida.common.io;

import lombok.NoArgsConstructor;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class EidaFileFacade {

    public static void createFile(String filePath, String... columns) throws FileNotFoundException {
        File file = new File(filePath);
        if (file.exists()) return;

        try (FileOutputStream output = new FileOutputStream(file)) {
            String header = String.join(", ", columns);
            output.write(header.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
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
