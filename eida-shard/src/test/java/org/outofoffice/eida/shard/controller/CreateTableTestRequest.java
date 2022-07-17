package org.outofoffice.eida.shard.controller;

import lombok.Data;
import org.outofoffice.common.testing.EidaSocketTestFacade;
import org.outofoffice.common.testing.TestRequest;

@Data
public class CreateTableTestRequest implements TestRequest {

    private final String address = "localhost:10830";
    private final String message = "create table, test";

    public static void main(String[] args) {
        EidaSocketTestFacade.request(new CreateTableTestRequest());
    }

}
