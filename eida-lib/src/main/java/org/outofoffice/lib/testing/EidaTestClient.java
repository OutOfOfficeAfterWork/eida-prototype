package org.outofoffice.lib.testing;

import lombok.extern.slf4j.Slf4j;
import org.outofoffice.lib.core.socket.EidaDefaultSocketClient;
import org.outofoffice.lib.core.socket.EidaSocketClient;
import org.outofoffice.lib.core.socket.EidaSocketClientLoggingProxy;

@Slf4j
public class EidaTestClient {

    private static final EidaSocketClient eidaClient = new EidaSocketClientLoggingProxy(new EidaDefaultSocketClient());


    public static void request(TestRequest req) {
        long start = System.currentTimeMillis();

        String address = req.getAddress();
        String message = req.getMessage();
        String request = address + ", " + message;
        String response = eidaClient.request(address, message);

        long end = System.currentTimeMillis();
        long duration = end - start;

        log.info("\n" +
            "\trequest: {}\n" +
            "\tresponse: {}\n" +
            "\tduration: {}", request, response, duration);
    }

}
