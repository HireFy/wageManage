package com.cobcap.wageManager.util;

import com.cobcap.wageManager.pojo.RewardService;
import com.cobcap.wageManager.service.DeptService;
import com.cobcap.wageManager.service.PersonService;
import com.cobcap.wageManager.service.PlaceService;
import com.cobcap.wageManager.service.SalaryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * @Description 生成数据
 * @Date Dec 02 10:56 PM
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class GenerateData {
    @Autowired
    private PersonService personService;

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private RewardService rewardService;

    /*人员的数据量*/
    private static int personDataSize = 100;

    @Test
    public void generate() {
        /*生成部门信息*/
//        deptService.generateDept();

        /*生成职位信息*/
//        placeService.generate();

        /*生成人员信息*/
//        personService.generatePerson(personDataSize);

        /*生成每个人的奖惩信息*/
//        rewardService.generateReward();

        /*生成工资信息*/
        salaryService.generateSalary();
    }



















    @Test
    public void calenderTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar time = Calendar.getInstance();
        Random random = new Random();
        time.set(Calendar.MONTH, 0);
        time.set(Calendar.DATE, 31);
        for (int i = 0; i < 12; i++) {
            System.out.println(sdf.format(time.getTime()));
            System.out.println(random.nextInt(time.getMaximum(Calendar.DAY_OF_MONTH)));
            time.add(Calendar.MONTH, 1);
            System.out.println("-----------------------");
        }
    }
    @Test
    public void random() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar time = Calendar.getInstance();
        time.set(Calendar.MONTH, 1);
        time.set(Calendar.DATE, 1);
        time.roll(Calendar.DATE, -1);

        System.out.println(sdf.format(time.getTime()));
        System.out.println(time.getTime());
    }

}
