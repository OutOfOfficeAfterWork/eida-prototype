package org.outofoffice.eidaspringsupport.api.dto;

import lombok.Data;
import org.outofoffice.lib.core.ddl.param.RenameTableParam;

@Data
public class RenameTableRequest {
    private String currentName;
    private String nextName;

    public RenameTableParam toParam() {
        return new RenameTableParam(
            currentName,
            nextName
        );
    }
}
