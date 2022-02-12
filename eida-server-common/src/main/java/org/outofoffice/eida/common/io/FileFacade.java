package org.outofoffice.eida.common.io;

import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class FileFacade {

    public static Map<String, String> readFileAsMap(String filePath) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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
