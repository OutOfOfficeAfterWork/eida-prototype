package org.outofoffice.eidaprototype.lib.core.socket;


@FunctionalInterface
public interface EidaClient {

    String request(String address, String message);

}
