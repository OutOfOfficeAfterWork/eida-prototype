package org.outofoffice.eida.manager.domain;

import java.util.*;
import java.util.stream.Collectors;

public class ShardMapping {

    private final Map<String, String> content;

    public ShardMapping() {
        content = new HashMap<>();
    }

    public ShardMapping(Map<String, String> content) {
        this.content = content;
    }

    public Map<String, String> copyContent() {
        return new HashMap<>(content);
    }

    public void appendRow(String shardId, String shardUrl) {
        content.put(shardId, shardUrl);
    }

    public Optional<String> getShardUrl(String shardId) {
        return Optional.ofNullable(content.get(shardId));
    }

    public List<String> getShardUrls(Set<String> shardIds) {
        return shardIds.stream()
            .map(shardId -> getShardUrl(shardId).orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    public Optional<String> getShardId(String shardUrl) {
        return content.entrySet().stream()
            .filter(e -> e.getValue().equals(shardUrl))
            .map(Map.Entry::getKey)
            .findFirst();
    }

    public Set<String> getAllShardIds() {
        return content.keySet();
    }

    public Set<String> getAllShardUrls() {
        return new HashSet<>(content.values());
    }
}
