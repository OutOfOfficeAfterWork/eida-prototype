package org.outofoffice.eida.shard.controller;

import lombok.Data;
import org.outofoffice.common.testing.EidaSocketTestFacade;
import org.outofoffice.common.testing.TestRequest;

@Data
public class DeleteTestRequest implements TestRequest {

    private final String address = "localhost:10830";
    private final String message = "delete, table {entityId}";

    public static void main(String[] args) {
        EidaSocketTestFacade.request(new DeleteTestRequest());
    }

}
