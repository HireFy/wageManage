package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.pojo.Salary;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface SalaryDao {
    Boolean deleteByDeptId(Integer id);

    Boolean deleteByPlaceId(Integer id);

    Salary getById(Integer id);

    Boolean deleteById(Integer id);

    Boolean updateById(Salary salary);

    Boolean insert(Salary salary);

    List<Salary> getSalaries(@Param("offset") int offset, @Param("size") int size);

    Boolean insertSalaries(List<Salary> salaryList);

    int getTotalCount();

    Person getPersonBySalaryId(Integer id);

    Boolean updateSalaryByPersonId(@Param("personId") Integer id, @Param("salary") BigDecimal salary);

    Boolean deleteByPersonId(Integer id);

    /*根据用户Id查找其所有工资信息*/
    List<Salary> getSalaryByPersonId(@Param("offset") int offset, @Param("size") int size, @Param("personId") Integer personId);

    /*获得指定id用户的工资记录的条数*/
    int getSalaryCountByPersonId(Integer personId);

    /*根据personId获得他工资记录中的年份列表*/
    List<Integer> getYearByPersonId(Integer personId);

    /*根据提供的personId, year, month信息查找工资信息*/
    List<Salary> getSalaryByPersonIdAndYearAndMonth(@Param("personId") Integer personId,
                                                    @Param("year") Integer year,
                                                    @Param("month") Integer month);

    int getCountByYear(@Param("personId") Integer personId, @Param("year") Integer year);

    List<Integer> getMonthsByPersonIdOrYear(@Param("personId") Integer personId, @Param("year") Integer year);

    Boolean isPersonSalaryExist(Integer personId);

    Salary getSalaryByPersonIdAndRecordDate(@Param("personId") Integer personId, @Param("recordDate") String recordDate);
}
