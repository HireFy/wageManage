package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.pojo.Salary;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SalaryDao {
    Salary getById(Integer id);

    Boolean deleteById(Integer id);

    Boolean updateById(Salary salary);

    Boolean insert(Salary salary);

    List<Salary> getSalaries(@Param("offset") int offset, @Param("size") int size);

    Boolean insertSalaries(List<Salary> salaryList);

    int getTotalCount();

    BigDecimal getSalaryByPersonId(Integer id);

    Person getPersonBySalaryId(Integer id);

    Boolean updateSalaryByPersonId(@Param("personId")Integer id, @Param("salary")BigDecimal salary);

    Boolean deleteByPersonId(Integer id);

    Integer getSalaryIdByPersonId(Integer personId);
}
