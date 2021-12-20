package org.outofoffice.lib.context;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static java.io.File.separator;

@Slf4j
@RequiredArgsConstructor
public class EidaPropertyLoader {

    private static final String fileName = "eida.properties";

    private final Class<?> mainClass;

    public String getManagerServerUrl() throws IOException {
        FileInputStream fileInputStream = getFileInputStream();
        Properties properties = new Properties();
        properties.load(fileInputStream);

        String managerServerUrl = properties.getProperty("managerServerUrl");
        log.info("Eida Property load: managerServerUrl - {}", managerServerUrl);
        return managerServerUrl;
    }

    private FileInputStream getFileInputStream() throws FileNotFoundException {
        String currentPath = mainClass.getResource(".").getPath();
        List<String> full = Arrays.asList(currentPath.split(separator));
        List<String> result = new ArrayList<>();
        for (String token : full) {
            if (token.equals("classes")) break;
            result.add(token);
        }

        String dir = String.join(separator, result);
        String path = String.join(separator, dir, "resources", "main", fileName);
        File file = new File(path);
        return new FileInputStream(file);
    }
}
