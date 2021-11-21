package org.outofoffice.lib.core.ui;

import org.outofoffice.lib.util.ClassUtils;


public interface EidaEntity<ID> {

    ID getId();

    void setId(ID id);

    default String getTableName() {
        return ClassUtils.toTableName((Class<? extends EidaEntity<ID>>) getClass());
    }

}
