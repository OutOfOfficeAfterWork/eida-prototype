package org.outofoffice.eida.manager.repository;


public interface SchemeRepository {
    void save(String tableName, String scheme);
    String findByName(String tableName);
    void rename(String currentName, String nextName);
}
