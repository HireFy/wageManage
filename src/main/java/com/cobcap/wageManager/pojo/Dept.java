package com.cobcap.wageManager.pojo;

import lombok.Data;

@Data
public class Dept {
    private Integer id;
    private String name;
    private Integer fatherId;

    public Dept() {

    }

    public Dept(String name, Integer fatherId) {
        this.name = name;
        this.fatherId = fatherId;
    }

    public Dept(Integer id, String name, Integer fatherId) {
        this.id = id;
        this.name = name;
        this.fatherId = fatherId;
    }

    public Dept(String name) {
        this.name = name;
    }
}
