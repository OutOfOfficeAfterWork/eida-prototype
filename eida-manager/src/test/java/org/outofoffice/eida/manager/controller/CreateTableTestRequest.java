package org.outofoffice.eida.manager.controller;

import lombok.Data;
import org.outofoffice.common.testing.EidaSocketTestFacade;
import org.outofoffice.common.testing.TestRequest;

@Data
public class CreateTableTestRequest  implements TestRequest {

    private final String address = "localhost:10325";
    private final String message = "create table, test c1,c2,c3";

    public static void main(String[] args) {
        EidaSocketTestFacade.request(new CreateTableTestRequest());
    }

}
