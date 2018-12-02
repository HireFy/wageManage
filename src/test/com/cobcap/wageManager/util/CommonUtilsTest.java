package com.cobcap.wageManager.util;

import com.cobcap.wageManager.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class CommonUtilsTest {

    /*生成数据总量*/
    private static int dataSize = 200;

    /*每次处理的数据量*/
    private static int pageSize = 10;

    @Autowired
    private PersonService personService;

    @Test
    public void getName() {
        CommonUtils com = new CommonUtils();
        System.out.println(CommonUtils.getRandomNameAndSex());
    }


    @Test
    /*测试生成密码*/
    public void generatePass() {
        System.out.println(CommonUtils.generatePass());
    }


    @Test
    public void randomDate() {
        System.out.println(CommonUtils.getRandomDate("1980-01-01", "1990-12-31"));
    }

    @Test
    public void sdfs() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date start = format.parse("1980-01-01");
        Date end = format.parse("2018-01-01");

        Calendar calStart = Calendar.getInstance();
        calStart.setTime(start);

        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(end);

        System.out.println(calEnd.get(Calendar.YEAR) - calStart.get(Calendar.YEAR));
    }


    @Test
    public void getAge() throws ParseException {
        String aa = "2010-05-04 12:34:23";

        Timestamp ts = Timestamp.valueOf(aa);

        System.out.println(CommonUtils.getAge(ts));
    }


    @Test
    public void getRandomFloat() {
        System.out.println(CommonUtils.getRandomFloat((float) 0.10));
    }
}