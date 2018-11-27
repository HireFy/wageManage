package com.cobcap.wageManager.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PersonVo {
    private Integer id;
    private String name;
    private BigDecimal salary;
    private Integer placeId;
    private String placeName;

    public PersonVo() {

    }
}
