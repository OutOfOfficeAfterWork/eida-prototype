package org.outofoffice.lib.core.query;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class EidaDllGeneratorTest {

    EidaDllGenerator dllGenerator = new EidaDllGenerator();


    @Test
    void createGetAllShardUrls() {
        String dllQuery = dllGenerator.createGetAllShardUrls("");
        assertThat(dllQuery).isEqualTo("get all");
    }

    @Test
    void createGetAllShardUrlsWithTableName() {
        String dllQuery = dllGenerator.createGetAllShardUrls("member");
        assertThat(dllQuery).isEqualTo("get all member");
    }

    @Test
    void createGetDestinationShardUrl() {
        String dllQuery = dllGenerator.createGetDestinationShardUrl("member");
        assertThat(dllQuery).isEqualTo("get dst member");
    }

    @Test
    void createGetSourceShardUrl() {
        String dllQuery = dllGenerator.createGetSourceShardUrl("member", 1L);
        assertThat(dllQuery).isEqualTo("get src member 1");
    }

}