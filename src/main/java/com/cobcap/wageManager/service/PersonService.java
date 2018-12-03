package com.cobcap.wageManager.service;

import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.vo.PersonVo;
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

    void generatePerson(int dataSize);

    void generatePerson();

    void generateSalary(int pageNum, int pageSize);

    Boolean update(Person person);

    List<Integer> getPersonIdPageNation(int pageNum, int pageSize);

    List<PersonVo> getPersonVos(int pageNum, int pageSize);

    String getNameById(Integer id);

    /*检验编号和密码是否正确*/
    Boolean isPassRight(Integer num, String pass);

    PersonVo transFormData(Person person);

    /*添加人员*/
    Boolean addUser(Person person);

    Person getPersonByName(String name);
}
