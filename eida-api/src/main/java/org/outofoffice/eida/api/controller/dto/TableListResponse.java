package org.outofoffice.eida.api.controller.dto;

import lombok.Data;

import java.util.List;


@Data
public class TableListResponse {
    private final List<String> tableNames;

    public static TableListResponse of(List<String> tableNames) {
        return new TableListResponse(tableNames);
    }
}
