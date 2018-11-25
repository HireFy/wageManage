package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class DeptDaoTest {

    @Autowired
    private DeptDao deptDao;

    @Test
    public void getById() {
        System.out.println(deptDao.getById(2));
    }

    @Test
    public void deleteById() {
        assertEquals(true, deptDao.deleteById(1));
    }

    @Test
    public void updateById() {
        Dept dept = new Dept(1, "后端部门", null);
        System.out.println(deptDao.updateById(dept));
    }

    @Test
    public void insert() {
        Dept dept = new Dept("产品部门");
        assertEquals(true, deptDao.insert(dept));
    }

    @Test
    public void isNameExist() {
        assertEquals(true, deptDao.isNameExist("技术部门"));
    }

    @Test
    public void getDepts() {
        System.out.println(deptDao.getDepts(0, 5));
    }
}