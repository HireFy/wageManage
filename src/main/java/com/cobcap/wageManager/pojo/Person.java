package com.cobcap.wageManager.pojo;

import com.cobcap.wageManager.dao.PlaceDao;
import com.cobcap.wageManager.service.PlaceService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Data
public class Person {
    private Integer id;
    private String name;
    private BigDecimal salary;
    private Integer placeId;


    public Person() {

    }

    public Person(String name, BigDecimal salary, Integer placeId) {
        this.name = name;
        this.salary = salary;
        this.placeId = placeId;
    }

    public Person(String name, Integer placeId) {
        this.name = name;
        this.placeId = placeId;
    }

    public Person(Integer id, String name, BigDecimal salary, Integer placeId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.placeId = placeId;
    }
}
