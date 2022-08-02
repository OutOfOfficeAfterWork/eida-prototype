package org.outofoffice.eida.api.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.api.service.client.*;
import org.outofoffice.eida.api.service.param.*;
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

    public void createColumn(CreateColumnParam param) {
        String tableName = param.getTableName();
        String columnName = param.getColumnName();
        String defaultValue = param.getDefaultValue();

        managerClient.createColumn(tableName, columnName);
        List<String> allShardUrls = managerClient.getAllShardUrls();
        allShardUrls.forEach(shardUrl -> shardClient.createColumn(shardUrl, tableName, defaultValue));
    }

    public void renameColumn(RenameColumnParam param) {
        String tableName = param.getTableName();
        String currentName = param.getCurrentName();
        String nextName = param.getNextName();

        managerClient.renameColumn(tableName, currentName, nextName);
    }

    public void deleteColumn(DeleteColumnParam param) {
        String tableName = param.getTableName();
        String columnName = param.getColumnName();

        int columnIndex = managerClient.deleteColumn(tableName, columnName);
        List<String> allShardUrls = managerClient.getAllShardUrls();
        allShardUrls.forEach(shardUrl -> shardClient.deleteColumn(shardUrl, tableName, columnIndex));
    }
}
