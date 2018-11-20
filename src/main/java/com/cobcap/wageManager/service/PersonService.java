package com.cobcap.wageManager.service;

import com.cobcap.wageManager.pojo.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonService {
    Person getById(Integer id);

    Boolean deleteById(Integer id);

    Boolean updateById(Person person);

    Boolean insert(Person person);

    Boolean isNameExist(String name);

    List<Person> getPersons(int pageNum, int pageSize);

    int getPageCount(int pageSize);

    void generatePerson();

    void generateSalary();
}
