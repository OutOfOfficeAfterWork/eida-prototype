package org.outofoffice.eidaspringsupport.api.dto;

import lombok.Data;
import org.outofoffice.lib.core.ddl.param.DeleteTableParam;

@Data
public class DeleteTableRequest {
    private String tableName;

    public DeleteTableParam toParam() {
        return new DeleteTableParam(
            tableName
        );
    }
}
