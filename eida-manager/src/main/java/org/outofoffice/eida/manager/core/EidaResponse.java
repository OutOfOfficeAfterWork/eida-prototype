package org.outofoffice.eida.manager.core;

import lombok.AllArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public class EidaResponse {
    private final String code;
    private final String body;

    @Override
    public String toString() {
        return code + "\n" + body;
    }

    public static EidaResponse create(String code, String body) {
        return new EidaResponse(code, body);
    }
}
