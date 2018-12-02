package com.cobcap.wageManager.vo;

import com.cobcap.wageManager.pojo.Salary;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Date Dec 02 3:00 PM
 **/
@Data
public class SalaryVo {
    private Integer id;
    private Integer personId;
    private String personName;
    private BigDecimal salary;

    public SalaryVo() {

    }
}
