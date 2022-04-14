package org.outofoffice.lib.testing;

import lombok.Data;

@Data
public class GetDestTestRequest implements TestRequest {

    private final String address = "localhost:10325";
    private final String message = "get dst, table";

    public static void main(String[] args) {
        EidaTestClient.request(new GetDestTestRequest());
    }

}
