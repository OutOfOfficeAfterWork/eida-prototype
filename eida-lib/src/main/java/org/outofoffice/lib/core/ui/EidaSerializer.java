package org.outofoffice.lib.core.ui;

import lombok.extern.slf4j.Slf4j;
import org.outofoffice.common.exception.EidaException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.joining;
import static org.outofoffice.lib.util.StringUtils.getterName;
import static org.outofoffice.lib.util.StringUtils.setterName;

@Slf4j
public class EidaSerializer {

    private static final String NULL_STRING = "<null>";


    public <T extends EidaEntity<ID>, ID> String serialize(T entity) {
        return doSerialize(entity);
    }

    private <T extends EidaEntity<ID>, ID> String doSerialize(T entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        return Arrays.stream(fields)
            .map(field -> getValue(entity, field))
            .map(Object::toString)
            .collect(joining(","));
    }

    private <T extends EidaEntity<ID>, ID> Object getValue(T entity, Field field) {
        try {
            Method getter = entity.getClass().getMethod(getterName(field.getName()));
            Object value = getter.invoke(entity);
            if (value instanceof EidaEntity<?>) {
                value = ((EidaEntity<?>) value).getId();
            }
            return Optional.ofNullable(value).orElse(NULL_STRING);
        } catch (Exception e) {
            throw new EidaException(e);
        }
    }


    public <T extends EidaEntity<ID>, ID> List<T> deserialize(String schemeString, String tableString, Class<T> entityClass) {
        try {
            return doDeserialize(schemeString, tableString, entityClass);
        } catch (Exception e) {
            throw new EidaException(e);
        }
    }

    private <T extends EidaEntity<ID>, ID> List<T> doDeserialize(String schemeString, String tableString, Class<T> entityClass) throws Exception {
        if (tableString.isEmpty()) return emptyList();

        String[] columns = schemeString.split(",");

        List<Method> setters = new ArrayList<>();
        List<Class<?>> types = new ArrayList<>();
        for (String column : columns) {
            Class<?> fieldType = entityClass.getDeclaredField(column).getType();
            Method setter = entityClass.getMethod(setterName(column), fieldType);

            setters.add(setter);
            types.add(fieldType);
        }

        Constructor<T> constructor = entityClass.getDeclaredConstructor();
        constructor.setAccessible(true);

        List<T> entityList = new ArrayList<>();
        String[] lines = tableString.split("\n");
        for (String line : lines) {
            String[] values = line.split(",");

            T entity = constructEntity(setters, types, constructor, values);
            entityList.add(entity);
        }
        return entityList;
    }

    private <T extends EidaEntity<ID>, ID> T constructEntity(List<Method> setters, List<Class<?>> types, Constructor<T> constructor, String[] values) throws Exception {
        T entity = constructor.newInstance();
        for (int j = 0; j < values.length; j++) {
            if (values[j].equals(NULL_STRING)) continue;

            Method setter = setters.get(j);
            Class<?> type = types.get(j);

            if (type.equals(Long.class)) {
                setter.invoke(entity, Long.parseLong(values[j]));
            } else if (type.equals(String.class)) {
                setter.invoke(entity, String.valueOf(values[j]));
            } else {
                Constructor<? extends EidaEntity> fieldClassConstructor = (Constructor<? extends EidaEntity>) type.getConstructor();
                EidaEntity linkedEntity = fieldClassConstructor.newInstance();
                Class<?> id = linkedEntity.getClass().getDeclaredMethod("getId").getReturnType();
                if (id.equals(Long.class)) {
                    linkedEntity.setId(Long.parseLong(values[j]));
                } else if (id.equals(String.class)) {
                    linkedEntity.setId(values[j]);
                }
                setter.invoke(entity, linkedEntity);
            }
        }
        return entity;
    }

}
