package org.outofoffice.lib.testing;

import lombok.Data;

@Data
public class GetSourceTestRequest implements TestRequest {

    private final String address = "localhost:10325";
    private final String message = "get src, table 1";

    public static void main(String[] args) {
        EidaTestClient.request(new GetSourceTestRequest());
    }

}
