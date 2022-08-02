package org.outofoffice.eida.api.controller.dto;

import lombok.Data;
import org.outofoffice.eida.api.service.param.RenameColumnParam;

@Data
public class RenameColumnRequest {
    private String tableName;
    private String currentName;
    private String nextName;

    public RenameColumnParam toParam() {
        return new RenameColumnParam(
            tableName,
            currentName,
            nextName
        );
    }
}
