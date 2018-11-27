package com.cobcap.wageManager.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlaceVo {
    private Integer id;
    private String name;
    private BigDecimal salary;
    private Integer deptId;
    private String deptName;

    public PlaceVo(){

    }

}
