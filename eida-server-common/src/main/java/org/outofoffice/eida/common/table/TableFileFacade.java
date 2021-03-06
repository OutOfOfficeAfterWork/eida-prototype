package org.outofoffice.eida.common.table;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.exception.EidaBadRequestException;
import org.outofoffice.eida.common.exception.TableNotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;
import static org.outofoffice.eida.common.table.Table.DELIMITER;

@RequiredArgsConstructor
public class TableFileFacade {

    private final String dirPath;


    public boolean existByName(String tableName) {
        String filePath = dirPath + tableName;
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }

    public Table findByName(String tableName) {
        String filePath = dirPath + tableName;

        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);

            Map<String, String> map = new HashMap<>();
            for (String line : lines) {
                int idx = line.indexOf(DELIMITER);
                String key = line.substring(0, idx);
                map.put(key, line);
            }
            return new Table(tableName, map);
        } catch (NoSuchFileException e) {
            throw new TableNotFoundException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    public void save(Table table) {
        String filePath = dirPath + table.getTableName();
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                Files.delete(path);
            }

            String lines = String.join("\n", table.copyContent().values());
            Files.write(path, lines.getBytes(UTF_8), CREATE);
        } catch (NoSuchFileException e) {
            throw new TableNotFoundException(e);
        } catch (FileAlreadyExistsException e) {
            throw new EidaBadRequestException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    public List<Table> findAll() {
        try {
            return Files.list(Paths.get(dirPath))
                .filter(file -> !Files.isDirectory(file))
                .map(file -> findByName(extractTableName(file)))
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private String extractTableName(Path file) {
        String filePath = file.getFileName().toString();
        int idx = filePath.lastIndexOf(File.separator);
        return filePath.substring(idx + 1);
    }


    public void delete(Table table) {
        String filePath = dirPath + table.getTableName();
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                Files.delete(path);
            }
        } catch (NoSuchFileException e) {
            throw new TableNotFoundException(e);
        } catch (FileAlreadyExistsException e) {
            throw new EidaBadRequestException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void clear() {
        throw new IllegalStateException("do not invoke");
    }

}
