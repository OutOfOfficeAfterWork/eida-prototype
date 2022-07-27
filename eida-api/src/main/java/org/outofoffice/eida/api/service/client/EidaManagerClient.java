package org.outofoffice.eida.api.service.client;

import lombok.RequiredArgsConstructor;
import org.outofoffice.common.socket.EidaSocketClient;
import org.outofoffice.eida.api.service.query.EidaDllGenerator;
import org.outofoffice.eida.api.service.query.EidaManagerDdlGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class EidaManagerClient {
    private final EidaSocketClient eidaClient;
    private final EidaDllGenerator dllGenerator;
    private final EidaManagerDdlGenerator ddlGenerator;

    @Value("${eida.manager-server-url}")
    private String managerServerUrl;

    public List<String> getAllShardUrls() {
        String dll = dllGenerator.createGetAllShardUrlQuery();
        String response = eidaClient.request(managerServerUrl, dll);
        return Arrays.stream(response.split(",")).collect(toList());
    }

    public void createTable(String tableName, List<String> columnNames) {
        String ddl = ddlGenerator.createCreateTableQuery(tableName, columnNames);
        eidaClient.request(managerServerUrl, ddl);
    }

    public void renameTable(String currentName, String nextName) {
        String ddl = ddlGenerator.createRenameTableQuery(currentName, nextName);
        eidaClient.request(managerServerUrl, ddl);
    }

    public void dropTable(String tableName) {
        String ddl = ddlGenerator.createDropTableQuery(tableName);
        eidaClient.request(managerServerUrl, ddl);
    }

    public void addShard(String url) {
        String dll = dllGenerator.createAddShardQuery(url);
        eidaClient.request(managerServerUrl, dll);
    }

    public List<String> getAllTables() {
        String ddl = ddlGenerator.createGetAllTablesQuery();
        String response = eidaClient.request(managerServerUrl, ddl);
        return Arrays.stream(response.split(",")).collect(toList());
    }
}
