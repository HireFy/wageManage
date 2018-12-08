package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Salary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class SalaryDaoTest {

    @Autowired
    private SalaryDao salaryDao;


    @Test
    public void insert() {
        Salary salary = new Salary(5, BigDecimal.valueOf(10000), BigDecimal.valueOf(100), BigDecimal.valueOf(300), BigDecimal.valueOf(3000), new Timestamp(System.currentTimeMillis()));

        System.out.println(salaryDao.insert(salary));
    }

    @Test
    public void getById() {
        System.out.println(salaryDao.getById(3));
    }

    @Test
    public void update() {
        System.out.println(salaryDao.updateById(new Salary(3, null, BigDecimal.valueOf(10), null, null, null, null)));
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
    public void getPersonBySalaryId() {
        System.out.println(salaryDao.getPersonBySalaryId(3));
    }

    @Test
    public void getSalaryByPersonId() {
        List<Salary> salaries = salaryDao.getSalaryByPersonId(1, 12, 1106);
        System.out.println(salaries);
    }

    @Test
    public void getYearByPersonId() {
        System.out.println(salaryDao.getYearByPersonId(1106));
    }

    @Test
    public void getSalaryByPersonIdAndYearAndMonth() {
        System.out.println(salaryDao.getSalaryByPersonIdAndYearAndMonth(1106, 2017, 1));
    }

    @Test
    public void getMonthsByPersonIdOrYear() {
        System.out.println(salaryDao.getMonthsByPersonIdOrYear(1106, 2015));
    }

}