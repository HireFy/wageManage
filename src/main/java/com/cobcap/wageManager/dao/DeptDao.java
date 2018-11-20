package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptDao {
    Dept getById(Integer id);

    Boolean deleteById(Integer id);

    Boolean updateById(Dept dept);

    Boolean insert(Dept dept);

    Boolean isNameExist(String name);

    List<Dept> getDepts(@Param("offset") int offset, @Param("size") int size);

    int getTotalCount();
}
