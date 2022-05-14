package org.outofoffice.eida.common.table;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.exception.EidaBadRequestException;
import org.outofoffice.eida.common.exception.TableNotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static org.outofoffice.eida.common.table.Table.DELIMITER;

@RequiredArgsConstructor
public class TableFileFacade {

    private final String dirPath;
    private final String schemePath;


    public Table findByName(String tableName) {
        String filePath = dirPath + tableName;

        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            String header = lines.get(0);

            Map<String, String> map = new HashMap<>();
            int size = lines.size();
            for (int i = 1; i < size; i++) {
                String line = lines.get(i);
                int idx = line.indexOf(DELIMITER);
                String key = line.substring(0, idx);
                map.put(key, line);
            }
            return new Table(tableName, header, map);
        } catch (NoSuchFileException e) {
            throw new TableNotFoundException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    public void save(Table table) {
        String filePath = dirPath + table.getTableName();
        String schemeFilePath = schemePath + table.getTableName();
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                Files.delete(path);
            }

            Path file = Files.createFile(path);
            List<String> columns = Files.readAllLines(Paths.get(schemeFilePath));
            String header = String.join(DELIMITER, columns) + "\n";
            Files.write(file, header.getBytes(UTF_8), CREATE);

            String lines = String.join("\n", table.copyContent().values());
            Files.write(path, lines.getBytes(UTF_8), APPEND);
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


    public void clear() {
        throw new IllegalStateException("do not invoke");
    }

}