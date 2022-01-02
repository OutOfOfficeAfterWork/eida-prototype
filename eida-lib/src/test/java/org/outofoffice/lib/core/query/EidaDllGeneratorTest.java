package org.outofoffice.lib.core.query;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class EidaDllGeneratorTest {

    EidaDllGenerator dllGenerator = new EidaDllGenerator();


    @Test
    void createGetAllShardUrlsWithTableName() {
        String dllQuery = dllGenerator.createGetAllShardUrlsQuery("member");
        assertThat(dllQuery).isEqualTo("get all member");
    }

    @Test
    void createGetDestinationShardUrl() {
        String dllQuery = dllGenerator.createGetDestinationShardUrlQuery("member");
        assertThat(dllQuery).isEqualTo("get dst member");
    }

    @Test
    void createGetSourceShardUrl() {
        String dllQuery = dllGenerator.createGetSourceShardUrlQuery("member", 1L);
        assertThat(dllQuery).isEqualTo("get src member 1");
    }

}