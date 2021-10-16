package org.outofoffice.eidaprototype.lib.core;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;


class EidaClientImplTest {

    EidaClient eidaClient = new EidaClientImpl();

    String host = "localhost";
    int port = 9999;
    String address = host + ":" + port;


    @Test
    void request() {
        instantServer(port, msg -> String.format("Received: %s", msg)).start();

        String response = eidaClient.request(address, "hello");

        assertThat(response).isEqualTo("Received: hello");
    }

    private Thread instantServer(int port, Function<String, String> responseGenerator) {
        return new Thread(() -> {
            try (
                    ServerSocket serverSocket = new ServerSocket(port);
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String request = reader.readLine();
                String response = responseGenerator.apply(request);
                writer.println(response);
            } catch (Exception e) {
                throw new EidaException(e);
            }
        });
    }

}
