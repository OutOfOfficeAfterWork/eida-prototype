package org.outofoffice.eida.api.controller.dto;

import lombok.Data;

import java.util.List;


@Data
public class SchemeResponse {
    private final List<String> columnNames;

    public static SchemeResponse of(List<String> columnNames) {
        return new SchemeResponse(columnNames);
    }
}
