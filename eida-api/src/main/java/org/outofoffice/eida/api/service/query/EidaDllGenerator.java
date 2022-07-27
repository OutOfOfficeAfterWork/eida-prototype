package org.outofoffice.eida.api.service.query;

import org.springframework.stereotype.Component;

@Component
public class EidaDllGenerator {

    public String createGetAllShardUrlQuery() {
        return "get all";
    }

    public String createAddShardQuery(String url) {
        return "add shard, " + url;
    }

}
