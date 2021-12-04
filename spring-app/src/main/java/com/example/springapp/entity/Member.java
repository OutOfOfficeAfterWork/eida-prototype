package com.example.springapp.entity;

import lombok.Data;
import org.outofoffice.lib.core.ui.EidaEntity;

@Data
public class Member implements EidaEntity<Long> {
    private Long id;
}
