package org.outofoffice.eidaprototype.lib.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class EidaSerializerImpl implements EidaSerializer {

    @Override
    public <T extends EidaEntity<ID>, ID> String serialize(T entity) {
        return null;
    }


    @Override
    public <T extends EidaEntity<ID>, ID> List<T> deserialize(String tableString, Class<T> entityClass) {
        try {
            return doDeserialize(tableString, entityClass);
        } catch (Exception e) {
            throw new EidaException(e);
        }
    }

    private <T extends EidaEntity<ID>, ID> List<T> doDeserialize(String tableString, Class<T> entityClass) throws Exception {
        String[] lines = tableString.split("\n\r");

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
        // TODO marking join column + Embeddable
        else {
            Constructor<? extends EidaEntity> fieldClassConstructor = (Constructor<? extends EidaEntity>) type.getConstructor();
            EidaEntity linkedEntity = fieldClassConstructor.newInstance();
            Class<?> id = linkedEntity.getClass().getField("id").getType();
            linkedEntity.setId(value);
        }
    }


    private <T extends EidaEntity<ID>, ID> Map<Method, Class<?>> columnsToSetterMap(String[] columns, Class<T> entityClass) throws Exception {
        Map<Method, Class<?>> setters = new LinkedHashMap<>();
        for (String column : columns) {
            Class<?> type = entityClass.getDeclaredField(column).getType();
            String c = String.valueOf(column.charAt(0));
            String setterName = column.replace(c, "set"+ c.toUpperCase());
            setters.put(entityClass.getMethod(setterName, type), type);
        }
        return setters;
    }

}
