package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.dao.BonusDao;
import com.cobcap.wageManager.dao.PersonDao;
import com.cobcap.wageManager.pojo.Bonus;
import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.service.BonusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class BonusServiceImplTest {

    @Autowired
    private BonusDao bonusDao;

    @Autowired
    private BonusService bonusService;

    @Autowired
    private PersonDao personDao;

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
    public void getBounses() {
    }

    /**
     * 插入随机奖金到数据库
     */
    @Test
    public void insertRandomBonus() {
//        List<Integer> personIds = personDao.getAllId();

        for(int i = 0; i < 21; i++){
            List<Integer> personIds = personDao.getPersonIdPageNation(i * 100, 100);

            bonusService.insertRandomBonus(personIds);
        }
    }

    @Test
    public void delete(){
        Bonus bonus = new Bonus(241);
        System.out.println(bonusService.delete(223));
    }

}