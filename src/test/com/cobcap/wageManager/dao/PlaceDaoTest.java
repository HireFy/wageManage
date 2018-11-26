package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Place;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class PlaceDaoTest {

    @Autowired
    private PlaceDao placeDao;

    @Test
    public void getById() {
        System.out.println(placeDao.getById(2));
    }

    @Test
    public void deleteById() {
        assertEquals(true, placeDao.deleteById(3));
    }

    @Test
    public void updateById() {
        Place place = new Place(2, null, BigDecimal.valueOf(40000.00), null);
        assertEquals(true, placeDao.updateById(place));
    }

    @Test
    public void insert() {
        Place place = new Place("财务主管", BigDecimal.valueOf(20000.00), 2);
        assertEquals(true, placeDao.insert(place));
    }

    @Test
    public void isNameExist() {
        assertEquals(true, placeDao.isNameExist("技术主管"));
        assertEquals(false, placeDao.isNameExist("营销主管"));
    }

    @Test
    public void getPlaces() {
        assertNotNull(placeDao.getPlaces(0, 5));
    }

    @Test
    public void getAllIds(){
        System.out.println(placeDao.getAllIds());
    }
}