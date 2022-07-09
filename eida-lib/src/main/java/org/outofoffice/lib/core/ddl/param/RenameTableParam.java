package org.outofoffice.lib.core.ddl.param;

import lombok.Data;

import java.util.List;

@Data
public class RenameTableParam {
    private final String currentName;
    private final String nextName;
}
