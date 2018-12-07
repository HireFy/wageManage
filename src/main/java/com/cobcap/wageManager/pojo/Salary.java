package com.cobcap.wageManager.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Salary {
    private Integer id;
    private Integer personId;
    private BigDecimal baseSalary;
    private BigDecimal overTimeSalary;
    private BigDecimal cutSalary;
    private BigDecimal finalSalary;
    private Timestamp recordDate;

    public Salary() {

    }


    public Salary(Integer personId, BigDecimal baseSalary, BigDecimal overTimeSalary, BigDecimal cutSalary, BigDecimal finalSalary, Timestamp recordDate) {
        this.personId = personId;
        this.baseSalary = baseSalary;
        this.overTimeSalary = overTimeSalary;
        this.cutSalary = cutSalary;
        this.finalSalary = finalSalary;
        this.recordDate = recordDate;
    }

    public Salary(Integer id, Integer personId, BigDecimal baseSalary, BigDecimal overTimeSalary, BigDecimal cutSalary, BigDecimal finalSalary, Timestamp recordDate) {
        this.id = id;
        this.personId = personId;
        this.baseSalary = baseSalary;
        this.overTimeSalary = overTimeSalary;
        this.cutSalary = cutSalary;
        this.finalSalary = finalSalary;
        this.recordDate = recordDate;
    }
}
