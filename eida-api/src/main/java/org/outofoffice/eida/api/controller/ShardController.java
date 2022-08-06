package org.outofoffice.eida.api.controller;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.api.controller.dto.AddShardRequest;
import org.outofoffice.eida.api.service.EidaDdlDispatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/shard")
@RequiredArgsConstructor
public class ShardController {
    private final EidaDdlDispatcher eidaDdlDispatcher;

    @PostMapping
    public ResponseEntity<Void> addShard(@RequestBody AddShardRequest addShardRequest) {
        eidaDdlDispatcher.addShard(addShardRequest.getUrl());
        return ResponseEntity.ok().build();
    }

//    public void readdressShard(String currentUrl, String nextUrl) {
//
//    }
//
//    public List<String> getAllShards() {
//
//    }

}
