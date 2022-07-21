package org.outofoffice.eida.api.controller.dto;

import lombok.Data;
import org.outofoffice.eida.api.service.param.DeleteTableParam;

@Data
public class DeleteTableRequest {
    private String tableName;

    public DeleteTableParam toParam() {
        return new DeleteTableParam(
            tableName
        );
    }
}
