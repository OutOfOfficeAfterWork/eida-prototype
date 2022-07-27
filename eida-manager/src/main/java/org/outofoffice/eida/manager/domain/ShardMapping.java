package org.outofoffice.eida.manager.domain;

import java.util.*;
import java.util.stream.Collectors;

public class ShardMapping {

    private final Map<Integer, String> content;

    public ShardMapping() {
        content = new HashMap<>();
    }

    public ShardMapping(Map<Integer, String> content) {
        this.content = content;
    }

    public Map<Integer, String> copyContent() {
        return new HashMap<>(content);
    }

    public void appendRow(String shardUrl) {
        boolean alreadyExist = content.values().stream()
            .anyMatch(v -> v.equals(shardUrl));
        if (alreadyExist) return;

        Integer shardId = content.size() + 1;
        content.put(shardId, shardUrl);
    }

    public Optional<String> getShardUrl(String shardId) {
        // TODO string to int
        return Optional.ofNullable(content.get(Integer.parseInt(shardId)));
    }

    public List<String> getShardUrls(Set<String> shardIds) {
        return shardIds.stream()
            .map(shardId -> getShardUrl(shardId).orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    public Optional<Integer> getShardId(String shardUrl) {
        return content.entrySet().stream()
            .filter(e -> e.getValue().equals(shardUrl))
            .map(Map.Entry::getKey)
            .findFirst();
    }

    public Set<Integer> getAllShardIds() {
        return content.keySet();
    }

    public Set<String> getAllShardUrls() {
        return new HashSet<>(content.values());
    }
}
