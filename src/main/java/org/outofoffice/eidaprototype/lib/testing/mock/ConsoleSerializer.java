package org.outofoffice.eidaprototype.lib.testing.mock;

import org.outofoffice.eidaprototype.lib.core.EidaEntity;
import org.outofoffice.eidaprototype.lib.core.EidaSerializer;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

import static org.outofoffice.eidaprototype.lib.util.LogUtils.indentedFormat;


@Slf4j
public class ConsoleSerializer implements EidaSerializer {

    @Override
    public <T extends EidaEntity<ID>, ID> String serialize(T entity) {
        log.info(indentedFormat("serialize {}"), entity);
        return "(" + entity.getId() + ")";
    }

    @Override
    public <T extends EidaEntity<ID>, ID> T deserialize(String result, Class<T> targetClass) {
        log.info(indentedFormat("deserialize {} as {}"), result, targetClass.getSimpleName());

        Object entity = null;
        try {
            Constructor<?> constructor = targetClass.getConstructors()[0];
            entity = constructor.newInstance(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) entity;
    }

}
