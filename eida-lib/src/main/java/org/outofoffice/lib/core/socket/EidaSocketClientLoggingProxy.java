package org.outofoffice.lib.core.socket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class EidaSocketClientLoggingProxy implements EidaSocketClient {

    private final EidaSocketClient socketClient;


    @Override
    public String request(String address, String message) {
        log.debug("request(address: {}, message: {})", address, message);
        String response = socketClient.request(address, message);
        log.debug("response: {}", response.replace("\n", "\\n"));
        return response;
    }

}
