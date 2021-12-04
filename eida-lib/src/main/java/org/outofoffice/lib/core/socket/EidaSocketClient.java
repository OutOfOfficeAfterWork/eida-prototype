package org.outofoffice.lib.core.socket;


@FunctionalInterface
public interface EidaSocketClient {

    String request(String address, String message);

}
