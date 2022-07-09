package org.outofoffice.lib.core.client;

import java.util.List;

public interface EidaDdlManagerClient {
    void createTable(String tableName, List<String> columnNames);
    void renameTable(String currentName, String nextName);
    void dropTable(String tableName);


}
