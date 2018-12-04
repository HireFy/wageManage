package com.cobcap.wageManager.service;

import com.cobcap.wageManager.pojo.Dept;

import java.util.List;

public interface DeptService {
    Dept getById(Integer id);

    Boolean deleteById(Integer id);

    Boolean updateById(Dept dept);

    Boolean insert(Dept dept);

    Boolean isNameExist(String name);

    List<Dept> getDepts(int pageNum, int pageSize);

    int getPageCount(int pageSize);

    List<Dept> getAllDepts();

    void generateDept();

    List<Dept> getDeptBykw(String kw);
}
