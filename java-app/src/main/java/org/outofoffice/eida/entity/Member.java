package org.outofoffice.eida.entity;

import org.outofoffice.lib.core.ui.EidaEntity;

public class Member implements EidaEntity<Long> {
    private Long id;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
