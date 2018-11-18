package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class PersonServiceImplTest {

    @Autowired
    private PersonService personService;

    @Test
    public void insert() {
        Person person = new Person("enim", 1);
        assertEquals(true, personService.insert(person));
    }
}