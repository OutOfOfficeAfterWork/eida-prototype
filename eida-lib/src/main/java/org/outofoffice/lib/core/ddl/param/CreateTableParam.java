package org.outofoffice.lib.core.ddl.param;

import lombok.Data;

import java.util.List;

@Data
public class CreateTableParam {
    private final String tableName;
    private final List<String> columnNames;
}
