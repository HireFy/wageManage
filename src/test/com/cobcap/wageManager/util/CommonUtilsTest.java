package com.cobcap.wageManager.util;

import com.cobcap.wageManager.service.BonusService;
import com.cobcap.wageManager.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class CommonUtilsTest {

    /*生成数据总量*/
    private static int dataSize = 200;

    /*每次处理的数据量*/
    private static int pageSize = 10;

    @Autowired
    private PersonService personService;

    @Autowired
    private BonusService bonusService;

    @Test
    public void getName() {
        CommonUtils com = new CommonUtils();
        System.out.println(CommonUtils.getRandomName());
    }


    @Test
    /*测试生成密码*/
    public void generatePass() {
        System.out.println(CommonUtils.generatePass());
    }



    /*生成数据*/
    /*place和dept的数据得自己写*/
    @Test
    public void generateData(){

        System.out.println("正在生成数据...");

        personService.generatePerson(dataSize);

        for (int i = 1; i < dataSize/pageSize + 1; i++) {
            List<Integer> personIds = personService.getPersonIdPageNation(i, pageSize);
            bonusService.insertRandomBonus(personIds);
        }
        personService.generateSalary(1, dataSize);

        System.out.println("数据生成成功!");
    }

}