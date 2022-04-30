package org.outofoffice.eida.manager.infrastructure;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.domain.ShardMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.join;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.util.stream.Collectors.joining;


@RequiredArgsConstructor
public class ShardMappingFileFacade {
    private static final String DELIMITER = ",";

    private final String filePath;

    public ShardMapping find() {
        String filePath = this.filePath;
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);

            Map<String, String> map = new HashMap<>();
            int size = lines.size();
            for (int i = 1; i < size; i++) {
                String line = lines.get(i);
                String[] tokens = line.split(DELIMITER, 2);
                map.put(tokens[0], tokens[1]);
            }
            return new ShardMapping(map);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void save(ShardMapping shardMapping) {
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                Files.delete(path);
            }

            Path file = Files.createFile(path);

            String[] columns = {"shardId", "shardUrl"};
            String header = join(DELIMITER, columns) + "\n";
            Files.write(file, header.getBytes(UTF_8), CREATE);

            String lines = shardMapping.copyContent().entrySet().stream()
                .map(e -> join(DELIMITER, e.getKey(), e.getValue()))
                .collect(joining("\n"));
            Files.write(path, lines.getBytes(UTF_8), APPEND);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
