package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Salary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class SalaryDaoTest {

    @Autowired
    private SalaryDao salaryDao;


    @Test
    public void insert() {
        Salary salary = new Salary(2, BigDecimal.valueOf(10000));

        System.out.println(salaryDao.insert(salary));
    }

    @Test
    public void getById() {
        System.out.println(salaryDao.getById(1));
    }

    @Test
    public void update() {
        System.out.println(salaryDao.updateById(new Salary(1, null, BigDecimal.valueOf(10))));
    }

    @Test
    public void getSalaries() {
        System.out.println(salaryDao.getSalaries(0, 5));
    }

    @Test
    public void getTotalCount() {
        System.out.println(salaryDao.getTotalCount());
    }

    @Test
    public void getSalaryByPersonId() {
        System.out.println(salaryDao.getSalaryByPersonId(2));
    }

    @Test
    public void getPersonBySalaryId() {
        System.out.println(salaryDao.getPersonBySalaryId(1));
    }
}