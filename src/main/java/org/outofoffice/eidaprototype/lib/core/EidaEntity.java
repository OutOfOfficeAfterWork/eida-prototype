package org.outofoffice.eidaprototype.lib.core;

import org.outofoffice.eidaprototype.lib.util.ClassUtils;


public interface EidaEntity<ID> {

    ID getId();

    void setId(ID id);

    default String getTableName() {
        return ClassUtils.toTableName((Class<? extends EidaEntity<ID>>) getClass());
    }

}
