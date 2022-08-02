package org.outofoffice.eida.api.service.param;

import lombok.Data;

@Data
public class RenameColumnParam {
    private final String tableName;
    private final String currentName;
    private final String nextName;
}
