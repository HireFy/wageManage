package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Attendance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class AttendanceDaoTest {

    @Autowired
    private AttendanceDao attendanceDao;

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
        Attendance att = new Attendance(1, null, (float) 0.8);
        assertEquals(true, attendanceDao.updateById(att));
    }

    @Test
    public void insert() {
        Attendance att = new Attendance(4, (float) 0.50);
        assertEquals(true, attendanceDao.insert(att));
    }

    @Test
    public void getAttendances() {
        System.out.println(attendanceDao.getAttendances(0, 3));
    }
}