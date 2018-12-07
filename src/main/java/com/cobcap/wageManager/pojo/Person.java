package com.cobcap.wageManager.pojo;

import com.cobcap.wageManager.dao.PlaceDao;
import com.cobcap.wageManager.service.PlaceService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Person {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private Timestamp born;
    private String pass;
    private Timestamp enterTime;
    private Integer placeId;

    public Person() {

    }

    public Person(Integer id, String name, String sex, Integer age, Timestamp born, String pass, Timestamp enterTime, Integer placeId) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.born = born;
        this.pass = pass;
        this.enterTime = enterTime;
        this.placeId = placeId;
    }

    public Person(String name, String sex, Integer age, Timestamp born, String pass, Timestamp enterTime, Integer placeId) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.born = born;
        this.pass = pass;
        this.enterTime = enterTime;
        this.placeId = placeId;
    }
}
