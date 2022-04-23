package org.outofoffice.eida.manager.controller;

import lombok.Data;
import org.outofoffice.common.testing.EidaSocketTestFacade;
import org.outofoffice.common.testing.TestRequest;

@Data
public class ReportInsertTestRequest implements TestRequest {

    private final String address = "localhost:10325";
    private final String message = "report insert, localhost:10832 user 6";

    public static void main(String[] args) {
        EidaSocketTestFacade.request(new ReportInsertTestRequest());
    }
}
