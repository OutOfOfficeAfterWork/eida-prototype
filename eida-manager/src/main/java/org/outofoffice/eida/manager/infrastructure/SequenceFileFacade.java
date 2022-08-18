package org.outofoffice.eida.manager.infrastructure;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;

@RequiredArgsConstructor
public class SequenceFileFacade {
    private final String filePath;

    public long find() {
        try {
            return Long.parseLong(Files.readString(Paths.get(filePath)).trim());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void save(long sequence) {
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                Files.delete(path);
            }

            Path file = Files.createFile(path);

            Files.write(file, String.valueOf(sequence).getBytes(UTF_8), CREATE);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
