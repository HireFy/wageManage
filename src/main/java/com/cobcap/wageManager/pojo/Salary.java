package com.cobcap.wageManager.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Salary {
    private Integer id;
    private Integer personId;
    private BigDecimal salary;

    public Salary() {

    }

    public Salary(Integer personId, BigDecimal salary) {
        this.personId = personId;
        this.salary = salary;
    }

    public Salary(Integer id, Integer personId, BigDecimal salary) {
        this.id = id;
        this.personId = personId;
        this.salary = salary;
    }
}
