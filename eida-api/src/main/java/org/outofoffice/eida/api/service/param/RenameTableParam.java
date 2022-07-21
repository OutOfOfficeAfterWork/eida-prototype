package org.outofoffice.eida.api.service.param;

import lombok.Data;

@Data
public class RenameTableParam {
    private final String currentName;
    private final String nextName;
}
