package com.eida.eidaprototype.lib.core;

import static com.eida.eidaprototype.lib.util.ClassUtils.toTableName;


public interface EidaEntity<ID> {

    ID getId();

    void setId(ID id);

    default String getTableName() {
        return toTableName((Class<? extends EidaEntity<ID>>) getClass());
    }

}
