package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Person;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PersonDao {
    Person getById(Integer id);

    Boolean deleteById(Integer id);

    Boolean updateById(Person person);

    Boolean insert(Person person);

    Boolean isNameExist(String name);

    List<Person> getPersons(@Param("offset") int offset, @Param("size") int size);

    List<Integer> getAllId();

    BigDecimal getBaseSalaryById(Integer id);

    int getTotalCount();

    List<Integer> getPersonIdPageNation(@Param("offset") int offset, @Param("size") int size);

    String getNameById(Integer id);

    List<Integer> getAllIdByPlaceId(@Param("placeId") Integer placeId);

    Boolean deleteByDeptId(Integer id);

    Boolean deleteByPlaceId(Integer id);
}
