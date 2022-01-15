package org.outofoffice.eida.manager.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@RequiredArgsConstructor
public class ManagerServer {

    private final int port;

    private final QueryDispatcher queryDispatcher = new QueryDispatcher();


    public void run() {
        try (
            ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("ManagerServer started!");
            while (true) {
                try (
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
                ) {
                    String request = reader.readLine();
                    log.debug("{}", request);
                    String response = queryDispatcher.send(request);
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
