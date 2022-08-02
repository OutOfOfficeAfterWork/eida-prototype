package org.outofoffice.eida.api.controller.dto;

import lombok.Data;
import org.outofoffice.eida.api.service.param.DeleteColumnParam;

@Data
public class DeleteColumnRequest {
    private String tableName;
    private String columnName;

    public DeleteColumnParam toParam() {
        return new DeleteColumnParam(
            tableName, columnName
        );
    }
}
