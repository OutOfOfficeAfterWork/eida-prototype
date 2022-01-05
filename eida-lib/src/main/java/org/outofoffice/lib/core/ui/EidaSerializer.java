package org.outofoffice.lib.core.ui;

import lombok.extern.slf4j.Slf4j;
import org.outofoffice.lib.exception.EidaException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static org.outofoffice.lib.util.StringUtils.getterName;
import static org.outofoffice.lib.util.StringUtils.setterName;

@Slf4j
public class EidaSerializer {

    public <T extends EidaEntity<ID>, ID> String serialize(T entity) {
        try {
            return doSerialize(entity);
        } catch (Exception e) {
            throw new EidaException(e);
        }
    }

    private <T extends EidaEntity<ID>, ID> String doSerialize(T entity) throws Exception {
        StringJoiner header = new StringJoiner(",");
        StringJoiner body = new StringJoiner(",");

        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            header.add(field.getName());
            body.add(getValue(entity, field).toString());
        }
        return header + " " + body;
    }

    private <T extends EidaEntity<ID>, ID> Object getValue(T entity, Field field) throws Exception {
        Method getter = entity.getClass().getMethod(getterName(field.getName()));
        Object value = getter.invoke(entity);
        if (value instanceof EidaEntity<?>) {
            value = ((EidaEntity<?>) value).getId();
        }
        return value;
    }


    public <T extends EidaEntity<ID>, ID> List<T> deserialize(String tableString, Class<T> entityClass) {
        try {
            return doDeserialize(tableString, entityClass);
        } catch (Exception e) {
            throw new EidaException(e);
        }
    }

    private <T extends EidaEntity<ID>, ID> List<T> doDeserialize(String tableString, Class<T> entityClass) throws Exception {
        String[] lines = tableString.split("\n");

        String header = lines[0];
        String[] columns = header.split(",");
        Map<Method, Class<?>> setterTypeMap = columnsToSetterMap(columns, entityClass);
        List<Method> setters = new ArrayList<>(setterTypeMap.keySet());
        List<Class<?>> types = new ArrayList<>(setterTypeMap.values());

        Constructor<T> constructor = entityClass.getDeclaredConstructor();
        constructor.setAccessible(true);

        List<T> entityList = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            String body = lines[i];
            String[] values = body.split(",");

            T entity = constructor.newInstance();
            for (int j = 0; j < values.length; j++) {
                setValue(entity, values[j], setters.get(j), types.get(j));
            }
            entityList.add(entity);
        }
        return entityList;
    }

    private <T extends EidaEntity<ID>, ID> void setValue(T entity, String value, Method setter, Class<?> type) throws Exception {
        if (type.equals(Long.class)) {
            setter.invoke(entity, Long.parseLong(value));
        } else if (type.equals(String.class)) {
            setter.invoke(entity, String.valueOf(value));
        }
        else {
            Constructor<? extends EidaEntity> fieldClassConstructor = (Constructor<? extends EidaEntity>) type.getConstructor();
            EidaEntity linkedEntity = fieldClassConstructor.newInstance();
            Class<?> id = linkedEntity.getClass().getDeclaredField("id").getType();
            if (id.equals(Long.class)) {
                linkedEntity.setId(Long.parseLong(value));
            } else if (id.equals(String.class)) {
                linkedEntity.setId(value);
            }
            setter.invoke(entity, linkedEntity);
        }
    }

    private <T extends EidaEntity<ID>, ID> Map<Method, Class<?>> columnsToSetterMap(String[] columns, Class<T> entityClass) throws Exception {
        Map<Method, Class<?>> setters = new LinkedHashMap<>();
        for (String column : columns) {
            Class<?> type = entityClass.getDeclaredField(column).getType();
            setters.put(entityClass.getMethod(setterName(column), type), type);
        }
        return setters;
    }

}
