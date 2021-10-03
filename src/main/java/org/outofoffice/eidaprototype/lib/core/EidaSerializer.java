package org.outofoffice.eidaprototype.lib.core;


public interface EidaSerializer {

    <T extends EidaEntity<ID>, ID> String serialize(T entity);

    <T extends EidaEntity<ID>, ID> T deserialize(String result, Class<T> targetClass);

}
