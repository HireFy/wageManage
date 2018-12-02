package com.cobcap.wageManager.util;

import com.cobcap.wageManager.service.DeptService;
import com.cobcap.wageManager.service.PersonService;
import com.cobcap.wageManager.service.PlaceService;
import com.cobcap.wageManager.service.SalaryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    /*人员的数据量*/
    private static int personDataSize = 100;

    @Test
    public void generate() {
        /*生成部门信息*/
        deptService.generateDept();
        /*生成职位信息*/
        placeService.generate();

        personService.generatePerson(personDataSize);
        salaryService.generateSalary();
    }
}
