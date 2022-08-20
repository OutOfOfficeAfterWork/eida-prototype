package org.outofoffice.eida.api.controller;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.api.controller.dto.*;
import org.outofoffice.eida.api.service.EidaDdlDispatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/table")
@RequiredArgsConstructor
public class TableController {
    private final EidaDdlDispatcher eidaDdlDispatcher;

    @PostMapping
    public ResponseEntity<Void> createTable(@RequestBody CreateTableRequest req) {
        eidaDdlDispatcher.createTable(req.toParam());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<TableListResponse> getAllTables() {
        List<String> tableNames = eidaDdlDispatcher.getAllTables();
        TableListResponse response = TableListResponse.of(tableNames);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{tableName}")
    public ResponseEntity<SchemeResponse> getScheme(@PathVariable String tableName) {
        List<String> columnNames = eidaDdlDispatcher.getScheme(tableName);
        SchemeResponse response = SchemeResponse.of(columnNames);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Void> renameTable(@RequestBody RenameTableRequest req) {
        eidaDdlDispatcher.renameTable(req.toParam());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTable(@RequestBody DeleteTableRequest req) {
        eidaDdlDispatcher.deleteTable(req.toParam());
        return ResponseEntity.ok().build();
    }
}
