package org.outofoffice.eida.manager.infrastructure;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.repository.SchemeRepository;

@RequiredArgsConstructor
public class SchemeFileRepository implements SchemeRepository {
    private final SchemeFileFacade schemeFileFacade;

    @Override
    public void save(String tableName, String scheme) {
        schemeFileFacade.save(tableName, scheme);
    }

    @Override
    public String findByName(String tableName) {
        return schemeFileFacade.findByName(tableName);
    }
}
