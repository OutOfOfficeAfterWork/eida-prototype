package com.eida.eidaprototype.lib.core;

import static com.eida.eidaprototype.lib.util.ClassUtils.toTableName;


public interface EidaEntity<ID> {

    ID getId();

    default String getTableName() {
        return toTableName(getClass());
    }

}
