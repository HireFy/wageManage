package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Reward;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.awt.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class RewardDaoTest {
    @Autowired
    private RewardDao rewardDao;

    @Test
    public void insert() {
        Reward reward = new Reward(5, 5, 3, new Timestamp(System.currentTimeMillis()));
        System.out.println(rewardDao.insert(reward));
    }

    @Test
    public void getById() {
        System.out.println(rewardDao.getById(1));
    }

    @Test
    public void update() {
        Reward reward = new Reward(1, null, 100, 100, new Timestamp(System.currentTimeMillis()));
        System.out.println(rewardDao.update(reward));
    }

    @Test
    public void getRewards() {
        System.out.println(rewardDao.getRewards(0, 5));
    }

    @Test
    public void getTotalCount() {
        System.out.println(rewardDao.getTotalCount());
    }


    @Test
    public void getAbsenceAndOver() {
        Map map = rewardDao.getAbsenceAndOver(906, Timestamp.valueOf("2015-09-30 02:40:53.583").toString());

        System.out.println(map);
    }
}