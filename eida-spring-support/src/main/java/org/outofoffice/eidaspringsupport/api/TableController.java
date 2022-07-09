package org.outofoffice.eidaspringsupport.api;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eidaspringsupport.api.dto.CreateTableRequest;
import org.outofoffice.eidaspringsupport.api.dto.DeleteTableRequest;
import org.outofoffice.eidaspringsupport.api.dto.RenameTableRequest;
import org.outofoffice.lib.core.ddl.EidaDdlDispatcher;
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
        eidaDdlDispatcher.create(req.toParam());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> renameTable(@RequestBody RenameTableRequest req) {
        eidaDdlDispatcher.rename(req.toParam());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTable(@RequestBody DeleteTableRequest req) {
        eidaDdlDispatcher.delete(req.toParam());
        return ResponseEntity.ok().build();
    }
}
