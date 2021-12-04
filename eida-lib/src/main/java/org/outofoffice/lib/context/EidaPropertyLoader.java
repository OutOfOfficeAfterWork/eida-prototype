package org.outofoffice.lib.context;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class EidaPropertyLoader {
    public String getManagerServerUrl() throws IOException {
        Properties properties = new Properties();
        File file = new File("./test.properties");
        FileInputStream fileInputStream = new FileInputStream(file);
        properties.load(fileInputStream);

        String managerServerUrl = properties.getProperty("managerServerUrl");
        log.info("Eida Property load: managerServerUrl - {}", managerServerUrl);
        return managerServerUrl;
    }
}
