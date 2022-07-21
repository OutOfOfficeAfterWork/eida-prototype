package org.outofoffice.eida.api.controller.dto;

import lombok.Data;
import org.outofoffice.eida.api.service.param.RenameTableParam;

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
