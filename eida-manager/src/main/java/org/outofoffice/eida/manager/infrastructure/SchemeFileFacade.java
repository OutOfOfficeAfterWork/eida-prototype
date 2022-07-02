package org.outofoffice.eida.manager.infrastructure;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.exception.EidaBadRequestException;
import org.outofoffice.eida.common.exception.TableNotFoundException;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;


@RequiredArgsConstructor
public class SchemeFileFacade {
    private final String dirPath;

    public String findByName(String tableName) {
        try {
            String schemeFilePath = dirPath + "/" + tableName;
            return Files.readString(Paths.get(schemeFilePath)).trim();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void save(String tableName, String scheme) {
        String filePath = dirPath + tableName;
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                Files.delete(path);
            }

            Files.write(path, scheme.getBytes(UTF_8), CREATE);
        } catch (NoSuchFileException e) {
            throw new TableNotFoundException(e);
        } catch (FileAlreadyExistsException e) {
            throw new EidaBadRequestException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
