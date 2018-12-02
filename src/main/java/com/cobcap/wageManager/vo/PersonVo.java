package com.cobcap.wageManager.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 用于前端展示
 * 前端直接展示工资
 */

@Data
public class PersonVo {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private Timestamp born;
    private String bornTimeStr;
    private String pass;
    private Timestamp enterTime;
    private String enterTimeStr;
    private Integer placeId;
    private String placeName;

    /*缺勤率*/
    private Float onDutyRate;
    private String onDutyRateStr;
    private Float overTimeRate;
    private String overTimeRateStr;
    private BigDecimal salary;

    public PersonVo() {

    }
}
