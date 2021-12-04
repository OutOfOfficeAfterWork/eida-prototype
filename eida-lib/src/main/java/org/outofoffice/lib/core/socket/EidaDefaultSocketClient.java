package org.outofoffice.lib.core.socket;

import org.outofoffice.lib.exception.EidaException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringJoiner;

import static java.nio.charset.StandardCharsets.UTF_8;


public class EidaDefaultSocketClient implements EidaSocketClient {

    @Override
    public String request(String address, String message) {
        try (
                Socket socket = socket(address);
                PrintWriter writer = writer(socket);
                BufferedReader reader = reader(socket)
        ) {
            writer.println(message);
            return response(reader);
        } catch (Exception e) {
            throw new EidaException(e);
        }
    }


    private Socket socket(String address) throws IOException {
        String[] addressTokens = address.split(":");
        String host = addressTokens[0];
        int port = Integer.parseInt(addressTokens[1]);
        return new Socket(host, port);
    }

    private PrintWriter writer(Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream(), true, UTF_8);
    }

    private BufferedReader reader(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
    }


    private String response(BufferedReader reader) throws IOException {
        StringJoiner responseJoiner = new StringJoiner("\n");
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            responseJoiner.add(line);
        }
        return responseJoiner.toString();
    }

}
