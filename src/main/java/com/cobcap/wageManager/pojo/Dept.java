package com.cobcap.wageManager.pojo;

import lombok.Data;

@Data
public class Dept {
    private Integer id;
    private String name;

    public Dept() {

    }

    public Dept(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dept(String name) {
        this.name = name;
    }
}
