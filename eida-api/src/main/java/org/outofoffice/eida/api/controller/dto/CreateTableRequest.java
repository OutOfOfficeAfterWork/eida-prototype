package org.outofoffice.eida.api.controller.dto;

import lombok.Data;
import org.outofoffice.eida.api.service.param.CreateTableParam;

import java.util.List;

@Data
public class CreateTableRequest {

    private String tableName;
    private List<String> columnNames;

    public CreateTableParam toParam() {
        return new CreateTableParam(
            tableName, columnNames
        );
    }
}