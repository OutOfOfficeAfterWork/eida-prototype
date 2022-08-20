package org.outofoffice.lib.core.ui;

import org.outofoffice.lib.util.ClassUtils;


public interface EidaEntity<ID> {
    // @Id(auto-generated="true")
    // 컴파일 시점 또는 런타임에 auto-generated라면 ID타입이 정수인지 검사한다
    ID getId();

    void setId(ID id);

    default String getTableName() {
        return ClassUtils.toTableName((Class<? extends EidaEntity<ID>>) getClass());
    }

}
