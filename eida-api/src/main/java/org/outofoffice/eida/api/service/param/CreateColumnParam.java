package org.outofoffice.eida.api.service.param;

import lombok.Data;

@Data
public class CreateColumnParam {
    private final String tableName;
    private final String columnName;
    private final String defaultValue;
}
