package com.cobcap.wageManager.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Description 月底奖惩
 * @Date Dec 05 12:29 PM
 **/
@Data
public class Reward {
    private Integer id;
    private Integer personId;
    private Integer absenceDays;
    private Integer overTimeDays;
    private Timestamp recordDate;

    public Reward() {

    }

    public Reward(Integer id, Integer personId, Integer absenceDays, Integer overTimeDays, Timestamp recordDate) {
        this.id = id;
        this.personId = personId;
        this.absenceDays = absenceDays;
        this.overTimeDays = overTimeDays;
        this.recordDate = recordDate;
    }

    public Reward(Integer personId, Integer absenceDays, Integer overTimeDays, Timestamp recordDate) {
        this.personId = personId;
        this.absenceDays = absenceDays;
        this.overTimeDays = overTimeDays;
        this.recordDate = recordDate;
    }
}
