package org.outofoffice.eidaprototype.lib.core;

import java.util.List;


public interface EidaSerializer {

    <T extends EidaEntity<ID>, ID> String serialize(T entity);

    <T extends EidaEntity<ID>, ID> List<T> deserialize(String tableString, Class<T> entityClass);

}
