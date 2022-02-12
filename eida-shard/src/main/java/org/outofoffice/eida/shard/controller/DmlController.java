package org.outofoffice.eida.shard.controller;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.shard.repository.TableRepository;


@RequiredArgsConstructor
public class DmlController {
    public static final DmlController INSTANCE = new DmlController(
        new TableRepository() {
            @Override
            public String findByTableNameAndId(String tableName, String id) {
                return "id,teamName\n1,OutOfOffice";
            }
        }
    );

    private final TableRepository tableRepository;

    public String selectAll(String tableName) {
        return "select all, " + tableName;
    }

    public String selectById(String tableName, String id) {
        return tableRepository.findByTableNameAndId(tableName, id);
    }

    public void insert(String tableName, String data) {
        // return "insert, " + tableName + " " + data;
    }

    public void update(String tableName, String data) {
        // return "update, " + tableName + " " + data;
    }

    public void delete(String tableName, String id) {
        // return "delete, " + tableName + " " + id;
    }
}
