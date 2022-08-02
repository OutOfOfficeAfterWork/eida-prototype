package org.outofoffice.eida.api.service.param;

import lombok.Data;

@Data
public class DeleteColumnParam {
    private final String tableName;
    private final String columnName;
}
