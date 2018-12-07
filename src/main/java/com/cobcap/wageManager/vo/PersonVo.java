package com.cobcap.wageManager.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 用于前端展示
 *
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

    public PersonVo() {

    }
}
