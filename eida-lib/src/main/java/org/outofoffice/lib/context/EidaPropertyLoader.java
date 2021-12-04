package org.outofoffice.lib.context;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EidaPropertyLoader {
    public String getManagerServerUrl() {
        String managerServerUrl = "http://manager:1234";
        log.info("Eida Property load: managerServerUrl - {}", managerServerUrl);
        return managerServerUrl;
    }
}
