package org.outofoffice.lib.core;

import org.outofoffice.lib.core.socket.EidaDefaultSocketClient;
import org.outofoffice.lib.core.socket.EidaSocketClient;
import org.outofoffice.lib.core.socket.EidaSocketClientLoggingProxy;

import static org.assertj.core.api.Assertions.assertThat;

public class RealSocketTester {

    private static final EidaSocketClient eidaClient = new EidaSocketClientLoggingProxy(new EidaDefaultSocketClient());

    public static void main(String[] args) {
        String host = "localhost";
        int port = 10325;
        String address = host + ":" + port;

        long start = System.currentTimeMillis();
        String response = eidaClient.request(address, "get dest, hehe");
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println(duration);

        assertThat(response).isEqualTo("127.0.0.1:10830");
    }

}
