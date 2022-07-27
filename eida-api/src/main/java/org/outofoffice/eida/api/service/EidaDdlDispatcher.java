package org.outofoffice.eida.api.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.api.service.client.*;
import org.outofoffice.eida.api.service.param.CreateTableParam;
import org.outofoffice.eida.api.service.param.DeleteTableParam;
import org.outofoffice.eida.api.service.param.RenameTableParam;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class EidaDdlDispatcher {

    private final EidaManagerClient managerClient;
    private final EidaShardClient shardClient;

    public void createTable(CreateTableParam param) {
        String tableName = param.getTableName();
        List<String> columnNames = param.getColumnNames();

        managerClient.createTable(tableName, columnNames);
        List<String> allShardUrls = managerClient.getAllShardUrls();
        allShardUrls.forEach(shardUrl -> shardClient.createTable(shardUrl, tableName));
    }

    public void renameTable(RenameTableParam param) {
        String currentName = param.getCurrentName();
        String nextName = param.getNextName();

        managerClient.renameTable(currentName, nextName);
        List<String> allShardUrls = managerClient.getAllShardUrls();
        allShardUrls.forEach(shardUrl -> shardClient.renameTable(shardUrl, currentName, nextName));
    }

    public void deleteTable(DeleteTableParam param) {
        String tableName = param.getTableName();

        managerClient.dropTable(tableName);
        List<String> allShardUrls = managerClient.getAllShardUrls();
        allShardUrls.forEach(shardUrl -> shardClient.dropTable(shardUrl, tableName));
    }

    public void addShard(String url) {
        managerClient.addShard(url);
        List<String> tables = managerClient.getAllTables();
        tables.forEach(table -> shardClient.createTable(url, table));
    }

}
