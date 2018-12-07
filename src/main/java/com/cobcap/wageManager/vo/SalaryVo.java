package com.cobcap.wageManager.vo;

import com.cobcap.wageManager.pojo.Salary;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Description
 * @Date Dec 02 3:00 PM
 **/
@Data
public class SalaryVo {
    private Integer id;
    private Integer personId;
    private String personName;
    private BigDecimal baseSalary;
    private BigDecimal overTimeSalary;
    private BigDecimal cutSalary;
    private BigDecimal finalSalary;
    private Timestamp recordDate;
    private String recordDateStr;

    public SalaryVo() {

    }
}
