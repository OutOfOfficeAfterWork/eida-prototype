package org.outofoffice.eidaprototype.lib.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


public class EidaSerializerImpl implements EidaSerializer {

    @Override
    public <T extends EidaEntity<ID>, ID> String serialize(T entity) {
        return null;
    }


    // TODO refactoring
    @Override
    public <T extends EidaEntity<ID>, ID> T deserialize(String result, Class<T> targetClass) {
        T entity = null;

        try {
            Constructor<T> constructor = targetClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            entity = constructor.newInstance();

            String[] lines = result.split("\n\r");

            String header = lines[0];
            String[] columns = header.split(",");

            String body = lines[1];
            String[] values = body.split(",");

            Map<Method, Class<?>> setterMap = columnsToSetters(columns, targetClass);

            for (int i = 0; i < values.length; i++) {
                ArrayList<Method> setters = new ArrayList<>(setterMap.keySet());
                ArrayList<Class<?>> types = new ArrayList<>(setterMap.values());

                Method method = setters.get(i);
                Class<?> fieldClass = types.get(i);


                // 지원하는 타입별로 분기 추가 and 로직 분리
                /*
                  Map<Class<?>, Function<String, Object>> typeConverterMap;
                  ex) k: Long.class, v: (valueStr) -> Long.parseLong(valueStr)

                  Function<String, Object> typeConverter = typeConverterMap.get(fieldClass);
                  Object mappedValue = typeConverter.apply(valueStr)
                 */
                if (fieldClass.equals(Long.class)) {
                    method.invoke(entity, Long.parseLong(values[i]));
                } else if (fieldClass.equals(String.class)) {
                    method.invoke(entity, String.valueOf(values[i]));
                }
                // 연관관게가 있는 필드라고 일단 생각 or 리턴문자열 헤더에 조인용 컬럼 마킹?
                else {
                    Constructor<? extends EidaEntity> fieldClassConstructor = (Constructor<? extends EidaEntity>) fieldClass.getConstructor();
                    EidaEntity linkedEntity = fieldClassConstructor.newInstance();
                    Class<?> id = linkedEntity.getClass().getField("id").getType();
                    linkedEntity.setId(values[i]);
                }
            }

        } catch (Exception e) {

        }
        return entity;
    }

    private <T extends EidaEntity<ID>, ID> Map<Method, Class<?>> columnsToSetters(String[] columns, Class<T> targetClass) {
        Map<Method, Class<?>> setters = new LinkedHashMap<>();
        Arrays.stream(columns)
                .forEach(column -> {
                    Class<?> type = null;
                    try {
                        type = targetClass.getDeclaredField(column).getType();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    String c = String.valueOf(column.charAt(0));
                    String setterName = column.replace(c, "set" + c.toUpperCase());
                    try {
                        setters.put(targetClass.getMethod(setterName, type), type);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                });
        return setters;
    }

}
