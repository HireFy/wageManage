package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.dao.PersonDao;
import com.cobcap.wageManager.dao.PlaceDao;
import com.cobcap.wageManager.dao.SalaryDao;
import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.pojo.Salary;
import com.cobcap.wageManager.service.SalaryService;
import com.cobcap.wageManager.util.CommonUtils;
import com.cobcap.wageManager.vo.SalaryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.FlatteningPathIterator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryDao salaryDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PlaceDao placeDao;

    @Override
    public Integer getSalaryIdByPersonId(Integer personId) {
        return salaryDao.getSalaryIdByPersonId(personId);
    }

    @Override
    public Salary getById(Integer id) {
        return salaryDao.getById(id);
    }

    @Override
    public Boolean deleteById(Integer id) {
        return salaryDao.deleteById(id);
    }

    @Override
    public Boolean updateById(Salary salary) {
        return salaryDao.updateById(salary);
    }

    @Override
    public Boolean insert(Salary salary) {
        return salaryDao.insert(salary);
    }

    @Override
    public List<Salary> getSalaries(int pageNum, int pageSize) {
        return salaryDao.getSalaries((pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public int getTotalCount() {
        return salaryDao.getTotalCount();
    }

    @Override
    public BigDecimal getSalaryByPersonId(Integer id) {
        return salaryDao.getSalaryByPersonId(id);
    }

    @Override
    public Person getPersonBySalaryId(Integer id) {
        return salaryDao.getPersonBySalaryId(id);
    }

    @Override
    public Boolean updateSalaryByPersonId(Integer personId, BigDecimal salary) {
        return salaryDao.updateSalaryByPersonId(personId, salary);
    }

    @Override
    public Boolean deleteByPersonId(Integer id) {
        return salaryDao.deleteByPersonId(id);
    }

    /**
     * 分页获取SalaryVo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<SalaryVo> getSalaryVOs(int pageNum, int pageSize) {
        return this.transFormData(this.getSalaries(pageNum, pageSize));
    }

    @Override
    public int getPageCount(int pageSize) {
        int totalCount = salaryDao.getTotalCount();
        int pageCount = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            pageCount += 1;
        }
        return pageCount;
    }

    /**
     * 更新已有salay
     */
    public Boolean updateSalary(Integer placeId) {
        Person person;
        BigDecimal baseSalary;
        Float onDutyRate;
        Float overTimeRate;
        Salary salary;

        List<Integer> personIds = personDao.getAllIdByPlaceId(placeId);

        for (Integer id : personIds) {
            person = personDao.getById(id);
            baseSalary = placeDao.getSalaryByPlaceId(placeId);
            onDutyRate = person.getOnDutyRate();
            overTimeRate = person.getOverTimeRate();

            BigDecimal computeSalary = CommonUtils.computeSalary(baseSalary, onDutyRate, overTimeRate);


            this.updateById(new Salary(this.getSalaryIdByPersonId(id), null, computeSalary));
        }

        return true;
    }


    /**
     * 计算出(缺)勤率和加班率
     * 生成工资
     */
    public void generateSalary() {
        Person person;
        BigDecimal baseSalary;
        Float onDutyRate;
        Float overTimeRate;
        Salary salary;

        List<Integer> personIds = personDao.getAllId();

        for (Integer id : personIds) {
            person = personDao.getById(id);
            baseSalary = placeDao.getSalaryByPlaceId(person.getPlaceId());
            onDutyRate = person.getOnDutyRate();
            overTimeRate = person.getOverTimeRate();

            BigDecimal computeSalary = CommonUtils.computeSalary(baseSalary, onDutyRate, overTimeRate);

            this.insert(new Salary(id, computeSalary));
        }
    }

    /**
     * 转换Salary到SalaryVo
     * @param salary
     * @return
     */
    public SalaryVo transFormData(Salary salary) {
        SalaryVo vo = new SalaryVo();
        BeanUtils.copyProperties(salary, vo);

        vo.setPersonName(personDao.getById(vo.getPersonId()).getName());

        return vo;
    }

    public List<SalaryVo> transFormData(List<Salary> salaries) {
        List<SalaryVo> salaryVoList = new ArrayList<>();
        for (Salary salary:salaries) {
            salaryVoList.add(this.transFormData(salary));
        }

        return salaryVoList;
    }
}
