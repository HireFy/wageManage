package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.pojo.Salary;
import com.cobcap.wageManager.service.SalaryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class SalaryServiceImplTest {

    @Autowired
    private SalaryService salaryService;

    @Test
    public void getById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void updateById() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void getSalaries() {
    }

    @Test
    public void getTotalCount() {
    }

    @Test
    public void getSalaryByPersonId() {
    }

    @Test
    public void getPersonBySalaryId() {
    }

    /**
     * 根据已经生成的person生成salary表的数据
     */
    @Test
    public void generateSalary() {
        salaryService.generateSalary();
    }
}