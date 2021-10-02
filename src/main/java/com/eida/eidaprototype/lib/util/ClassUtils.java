package com.eida.eidaprototype.lib.util;

import com.eida.eidaprototype.lib.core.EidaEntity;


public class ClassUtils {

    public static <T extends EidaEntity<ID>, ID> String toTableName(Class<T> entityClass) {
        return entityClass.getSimpleName();
    }

}
