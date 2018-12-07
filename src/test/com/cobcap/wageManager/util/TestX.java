package com.cobcap.wageManager.util;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Date Dec 05 9:44 PM
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class TestX {

    @Test
    public void test() {
        int pageCount = 42 / 10;
        if (42 % 10 != 0) {
            pageCount += 1;
        }

        System.out.println(pageCount);
    }
}
