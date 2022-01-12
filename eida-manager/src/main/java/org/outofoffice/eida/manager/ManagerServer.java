package org.outofoffice.eida.manager;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;

@Slf4j
public class ManagerServer {

    public static void main(String[] args) {
        int port = 10325;
        Function<String, String> responseGenerator = (s) -> String.join(" ", s.split(""));

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("ManagerServer started!");
            while (true) {
                try (
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
                ) {
                    String request = reader.readLine();
                    String response = responseGenerator.apply(request);
                    log.info("{} -> {}", request, response);
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
