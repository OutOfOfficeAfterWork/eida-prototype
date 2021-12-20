package org.outofoffice.lib.context;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.outofoffice.lib.core.ui.EidaRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.io.File.separator;

@Slf4j
@RequiredArgsConstructor
public class EidaRepositoryScanner {

    private static final String ABSTRACT_REPOSITORY_NAME = EidaRepository.class.getName();
    private static final String GRADLE_MAIN_PATH = "/build/classes/java/main/";
    private static final String GRADLE_TEST_PATH = "/build/classes/java/test/";
    private static final String EXTENSION = ".class";

    private final Class<?> mainClass;

    public List<String> scan() throws IOException {
        String currentPath = mainClass.getResource(".").getPath();
        String rootDir = currentPath.replace(GRADLE_TEST_PATH, GRADLE_MAIN_PATH).split(GRADLE_MAIN_PATH)[0] + GRADLE_MAIN_PATH;
        try (Stream<Path> walk = Files.walk(Paths.get(rootDir))) {
            List<String> names = walk
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(s -> s.endsWith("Repository.class"))
                    .map(this::pathToPackage)
                    .filter(s -> !s.equals(ABSTRACT_REPOSITORY_NAME))
                    .collect(Collectors.toList());
            log.info("Eida repository scan: names- {}", names);
            return names;
        }
    }

    private String pathToPackage(String path) {
        String relPath = path.split(GRADLE_MAIN_PATH)[1];
        return relPath.replace(EXTENSION, "").replace(separator, ".");
    }
}
