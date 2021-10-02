package com.eida.eidaprototype.lib.util;

import com.eida.eidaprototype.lib.core.EidaEntity;
import com.eida.eidaprototype.lib.core.EidaRepository;

import java.lang.reflect.ParameterizedType;


public class ClassUtils {

    public static <T extends EidaEntity<ID>, ID> String toTableName(Class<T> entityClass) {
        return entityClass.getSimpleName();
    }

    public static <T extends EidaEntity<ID>, ID> Class<T> toEntityClass(Class<? extends EidaRepository<T, ID>> repositoryClass) {
        ParameterizedType generic = (ParameterizedType) repositoryClass.getGenericSuperclass();
        return (Class<T>) generic.getActualTypeArguments()[0];
    }

}
