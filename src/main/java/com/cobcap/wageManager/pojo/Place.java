package com.cobcap.wageManager.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Place {
    private Integer id;
    private String name;
    private BigDecimal salary;
    private Integer deptId;

    public Place() {

    }

    public Place(Integer id, String name, BigDecimal salary, Integer deptId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.deptId = deptId;
    }

    public Place(String name, BigDecimal salary, Integer deptId) {
        this.name = name;
        this.salary = salary;
        this.deptId = deptId;
    }

    public Place(Integer id, BigDecimal salary) {
        this.id = id;
        this.salary = salary;
    }

    public Place(Integer id, Integer deptId) {
        this.id = id;
        this.deptId = deptId;
    }
}
