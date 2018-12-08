package com.cobcap.wageManager.service;

import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.pojo.Salary;
import com.cobcap.wageManager.vo.SalaryVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SalaryService {
    Integer getSalaryIdByPersonId(Integer personId);

    Salary getById(Integer id);

    Boolean deleteById(Integer id);

    Boolean updateById(Salary salary);

    Boolean insert(Salary salary);

    List<Salary> getSalaries(int pageNum, int pageSize);

    int getTotalCount();

    Person getPersonBySalaryId(Integer id);

    void generateSalary();

    int getPageCount(int pageSize);

    List<SalaryVo> getSalaryVOs(int pageNum, int pageSize);

    Boolean updateSalaryByPersonId(Integer id, BigDecimal salary);

    Boolean deleteByPersonId(Integer id);

    Boolean updateSalary(Integer placeId);

    List<Salary> getSalaryByPersonId(int pageNum, int pageSize, Integer personId);

    SalaryVo transFormData(Salary salary);

    List<SalaryVo> transFormData(List<Salary> salaryList);

    int getSalaryCountByPersonId(Integer personId);

    List<Integer> getYearByPersonId(Integer personId);

    int getSalaryCount(int totalCount, int pageSize);

    List<Salary> getSalaryByPersonIdAndYearAndMonth(Integer personId, Integer year, Integer month);

    int getCountByYear(Integer personId, Integer year);

    List<Integer> getMonthsByPersonIdOrYear(Integer personId, Integer year);
}
