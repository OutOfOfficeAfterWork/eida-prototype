package org.outofoffice.eida.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.outofoffice.eida.common.QueryDispatcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@RequiredArgsConstructor
public class ServerRunner {

    private final int port;
    private final QueryDispatcher queryDispatcher;

    public void run() {
        try (
            ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("started");
            while (true) {
                try (
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
                ) {
                    String request = reader.readLine();
                    log.debug("{}", request);
                    String response = queryDispatcher.send(request).toString();
                    writer.println(response);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
