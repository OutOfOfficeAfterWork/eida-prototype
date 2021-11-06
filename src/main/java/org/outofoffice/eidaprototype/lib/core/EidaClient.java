package org.outofoffice.eidaprototype.lib.core;


@FunctionalInterface
public interface EidaClient {

    String request(String address, String message);

}
