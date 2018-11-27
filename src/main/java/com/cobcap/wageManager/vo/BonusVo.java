package com.cobcap.wageManager.vo;

import lombok.Data;

@Data
public class BonusVo {
    private Integer id;
    private Integer personId;
    private String personName;
    private Float rate;
    private String rateFormat;

    public BonusVo() {

    }
}
