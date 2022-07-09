package org.outofoffice.lib.core.ddl;

import lombok.RequiredArgsConstructor;
import org.outofoffice.lib.core.client.EidaDdlManagerClient;
import org.outofoffice.lib.core.client.EidaDdlShardClient;
import org.outofoffice.lib.core.client.EidaDllClient;
import org.outofoffice.lib.core.ddl.param.CreateTableParam;
import org.outofoffice.lib.core.ddl.param.DeleteTableParam;
import org.outofoffice.lib.core.ddl.param.RenameTableParam;

import java.util.List;


@RequiredArgsConstructor
public class EidaDdlDispatcher {
    private final EidaDllClient eidaDllClient;
    private final EidaDdlManagerClient eidaDdlManagerClient;
    private final EidaDdlShardClient eidaDdlShardClient;

    public void createTable(CreateTableParam param) {
        String tableName = param.getTableName();
        List<String> columnNames = param.getColumnNames();

        eidaDdlManagerClient.createTable(tableName, columnNames);
        List<String> allShardUrls = eidaDllClient.getAllShardUrls();
        allShardUrls.forEach(shardUrl -> eidaDdlShardClient.createTable(shardUrl, tableName));

    }

    public void renameTable(RenameTableParam param) {
        String currentName = param.getCurrentName();
        String nextName = param.getNextName();

        eidaDdlManagerClient.renameTable(currentName, nextName);
        List<String> allShardUrls = eidaDllClient.getAllShardUrls();
        allShardUrls.forEach(shardUrl -> eidaDdlShardClient.renameTable(shardUrl, currentName, nextName));

    }

    public void deleteTable(DeleteTableParam param) {
        String tableName = param.getTableName();

        eidaDdlManagerClient.dropTable(tableName);
        List<String> allShardUrls = eidaDllClient.getAllShardUrls();
        allShardUrls.forEach(shardUrl -> eidaDdlShardClient.dropTable(shardUrl, tableName));

    }
}
