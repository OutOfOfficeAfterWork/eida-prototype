package org.outofoffice.eida.api.controller.dto;

import lombok.Data;
import org.outofoffice.eida.api.service.param.CreateColumnParam;

@Data
public class CreateColumnRequest {
    private String tableName;
    private String columnName;
    private String defaultValue;

    public CreateColumnParam toParam() {
        return new CreateColumnParam(
            tableName, columnName, defaultValue
        );
    }
}