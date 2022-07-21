package org.outofoffice.eida.api.controller;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.api.controller.dto.CreateTableRequest;
import org.outofoffice.eida.api.controller.dto.DeleteTableRequest;
import org.outofoffice.eida.api.controller.dto.RenameTableRequest;
import org.outofoffice.eida.api.service.EidaDdlDispatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
