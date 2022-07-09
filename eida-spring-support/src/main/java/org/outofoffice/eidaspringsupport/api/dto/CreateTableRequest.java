package org.outofoffice.eidaspringsupport.api.dto;

import lombok.Data;
import org.outofoffice.lib.core.ddl.param.CreateTableParam;

import java.util.List;

@Data
public class CreateTableRequest {

    private String tableName;
    private List<String> columnNames;

    public CreateTableParam toParam() {
        return new CreateTableParam(
            tableName, columnNames
        );
    }
}