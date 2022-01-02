package org.outofoffice.lib.util;

import org.outofoffice.lib.core.ui.EidaEntity;
import org.outofoffice.lib.core.ui.EidaRepository;

import java.lang.reflect.ParameterizedType;


public class ClassUtils {

    public static <T extends EidaEntity<ID>, ID> String toTableName(Class<T> entityClass) {
        return entityClass.getSimpleName();
    }

    public static <T extends EidaEntity<ID>, ID> Class<T> toEntityClass(Class<? extends EidaRepository<T, ID>> repositoryClass) {
        ParameterizedType generic = (ParameterizedType) repositoryClass.getGenericSuperclass();
        return (Class<T>) generic.getActualTypeArguments()[0];
    }

    public static String getterName(String fieldName) {
        String c = String.valueOf(fieldName.charAt(0));
        return fieldName.replaceFirst(c, "get"+ c.toUpperCase());
    }

    public static String setterName(String fieldName) {
        String c = String.valueOf(fieldName.charAt(0));
        return fieldName.replaceFirst(c, "set"+ c.toUpperCase());
    }

}
