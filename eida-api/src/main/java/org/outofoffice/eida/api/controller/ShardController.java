package org.outofoffice.eida.api.controller;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.api.service.EidaDdlDispatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/api/shard")
@RequiredArgsConstructor
public class ShardController {
    private final EidaDdlDispatcher eidaDdlDispatcher;

    @PostMapping
    public void addShard(String url) {
        eidaDdlDispatcher.addShard(url);
    }

//    public void readdressShard(String currentUrl, String nextUrl) {
//
//    }
//
//    public List<String> getAllShards() {
//
//    }

}
