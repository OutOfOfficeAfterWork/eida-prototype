package org.outofoffice.eida.manager.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.repository.SchemeRepository;

@RequiredArgsConstructor
public class SchemeService {
    private final SchemeRepository schemeRepository;

    public void save(String tableName, String scheme) {
        schemeRepository.save(tableName, scheme);
    }

    public String findByName(String tableName) {
        return schemeRepository.findByName(tableName);
    }
}
