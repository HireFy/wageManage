package com.cobcap.wageManager.pojo;

import lombok.Data;

@Data
public class Attendance {
    private Integer id;
    private Integer personId;
    private Float rate;

    public Attendance() {

    }

    public Attendance(Integer id, Integer personId, Float rate) {
        this.id = id;
        this.personId = personId;
        this.rate = rate;
    }

    public Attendance(Integer personId, Float rate) {
        this.personId = personId;
        this.rate = rate;
    }
}
