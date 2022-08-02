package org.outofoffice.eida.api.controller;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.api.controller.dto.CreateColumnRequest;
import org.outofoffice.eida.api.controller.dto.DeleteColumnRequest;
import org.outofoffice.eida.api.controller.dto.RenameColumnRequest;
import org.outofoffice.eida.api.service.EidaDdlDispatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/column")
@RequiredArgsConstructor
public class ColumnController {
    private final EidaDdlDispatcher eidaDdlDispatcher;

    @PostMapping
    public ResponseEntity<Void> createColumn(@RequestBody CreateColumnRequest req) {
        eidaDdlDispatcher.createColumn(req.toParam());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> renameColumn(@RequestBody RenameColumnRequest req) {
        eidaDdlDispatcher.renameColumn(req.toParam());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteColumn(@RequestBody DeleteColumnRequest req) {
        eidaDdlDispatcher.deleteColumn(req.toParam());
        return ResponseEntity.ok().build();
    }
}
