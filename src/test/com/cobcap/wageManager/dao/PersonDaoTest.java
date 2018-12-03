package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class PersonDaoTest {

    @Autowired
    private PersonDao personDao;

    @Test
    public void getById() {
        assertNotNull(personDao.getById(2));
        System.out.println(personDao.getById(2));
    }

    @Test
    public void deleteById() {
        assertEquals(true, personDao.deleteById(1));
    }

    @Test
    public void updateById() {
        Person person = new Person(2, "zxq", "女", null, null, null, null, null, null, null);

        assertEquals(true, personDao.updateById(person));
    }

    @Test
    public void insert() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
        Timestamp born = null;
        Timestamp enterTime = null;
        try {
            born = new Timestamp(dateFormat.parse("1997-12-09").getTime());
            enterTime = new Timestamp(dateFormat.parse("2020-12-3").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Person person = new Person("fujunwei", "男", 21, Timestamp.valueOf("1997-11-20 08:00:00.0"), "1234", Timestamp.valueOf("2018-01-01 08:00:00.0"), 1151, (float) 0.0, (float) 0.0);
        System.out.println("before: " + person.getId());
        System.out.println(personDao.insert(person));
        System.out.println(person.getId());
    }

    @Test
    public void isNameExist() {
        assertEquals(true, personDao.isNameExist("fjw"));
        assertEquals(false, personDao.isNameExist("sdfsdf"));
    }

    @Test
    public void getPersons() {
        assertNotNull(personDao.getPersons(0, 5));
    }

    @Test
    public void getAllId() {
        System.out.println(personDao.getAllId());
    }

    @Test
    public void getBaseSalary() {
        System.out.println(personDao.getBaseSalaryById(2));
    }


    @Test
    public void getPageCount() {
        System.out.println(personDao.getTotalCount());
    }

    @Test
    public void testTime() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Timestamp born = new Timestamp(dateFormat.parse("1997-12-09").getTime());
        Timestamp enterTime = new Timestamp(dateFormat.parse("2020-12-3").getTime());

        System.out.println(born);
        System.out.println(enterTime);
    }

    @Test
    public void deleteByDeptId() {
        System.out.println(personDao.deleteByDeptId(1016));
    }
}