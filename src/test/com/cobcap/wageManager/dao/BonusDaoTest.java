package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Bonus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class BonusDaoTest {

    @Autowired
    private BonusDao attendanceDao;

    @Autowired
    private PersonDao personDao;

    @Test
    public void getById() {
        System.out.println(attendanceDao.getById(1));
    }

    @Test
    public void deleteById() {
        assertEquals(true, attendanceDao.deleteById(1));
    }

    @Test
    public void updateById() {
        Bonus att = new Bonus(1, null, (float) 0.8);
        assertEquals(true, attendanceDao.updateById(att));
    }

    @Test
    public void insert() {
        Bonus att = new Bonus(4, (float) 0.50);
        assertEquals(true, attendanceDao.insert(att));
    }

    @Test
    public void getAttendances() {
        System.out.println(attendanceDao.getBounses(0, 3));
    }

    @Test
    public void insertIdList() {
//        assertEquals(true, attendanceDao.insert());
//        Bonus bonus = new Bonus();
//        List<Bonus> ss = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            bonus = new Bonus(1, (float)0.33);
//            ss.add(bonus);
//        }
//        System.out.println(ss);
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        System.out.println(decimalFormat.format(0 + (float)Math.random()*1.00));
    }

    @Test
    public void getRateByPersonId() {
        System.out.println(attendanceDao.getRateByPersonId(112));
    }
}