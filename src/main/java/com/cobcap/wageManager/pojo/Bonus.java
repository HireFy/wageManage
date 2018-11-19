package com.cobcap.wageManager.pojo;

import lombok.Data;

@Data
public class Bonus {
    private Integer id;
    private Integer personId;
    private Float rate;

    public Bonus() {

    }

    public Bonus(Integer id, Integer personId, Float rate) {
        this.id = id;
        this.personId = personId;
        this.rate = rate;
    }

    public Bonus(Integer personId, Float rate) {
        this.personId = personId;
        this.rate = rate;
    }
}
