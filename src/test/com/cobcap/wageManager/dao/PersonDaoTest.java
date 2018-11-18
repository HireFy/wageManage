package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class PersonDaoTest {

    @Autowired
    private PersonDao personDao;

    @Test
    public void getById() {
        assertNotNull(personDao.getById(4));
        System.out.println(personDao.getById(4));
    }

    @Test
    public void deleteById() {
        assertEquals(true, personDao.deleteById(1));
    }

    @Test
    public void updateById() {
        Person person = new Person(4, "fjw", null, null);
        assertEquals(true, personDao.updateById(person));
    }

    @Test
    public void insert() {
        Person person = new Person("quod", 2);
        assertEquals(true, personDao.insert(person));
    }

    @Test
    public void isNameExist() {
        assertEquals(true, personDao.isNameExist("quod"));
        assertEquals(false, personDao.isNameExist("sdfsdf"));
    }

    @Test
    public void getPersons() {
        assertNotNull(personDao.getPersons(0, 5));
    }
}