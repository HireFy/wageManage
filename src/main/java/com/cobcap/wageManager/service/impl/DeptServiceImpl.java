package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.dao.DeptDao;
import com.cobcap.wageManager.dao.PersonDao;
import com.cobcap.wageManager.dao.PlaceDao;
import com.cobcap.wageManager.dao.SalaryDao;
import com.cobcap.wageManager.pojo.Dept;
import com.cobcap.wageManager.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private SalaryDao salaryDao;

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private PersonDao personDao;

    @Override
    public Dept getById(Integer id) {
        return deptDao.getById(id);
    }

    /**
     * 删除部门，部门的职位删除，相关人员删除，工资删除
     *
     * @param id
     * @return
     */
    @Override
    public Boolean deleteById(Integer id) {
        /*工资表删除*/
        salaryDao.deleteByDeptId(id);
        /*人员删除*/
        personDao.deleteByDeptId(id);
        /*职位删除*/
        placeDao.deleteByDeptId(id);
        return deptDao.deleteById(id);

    }

    @Override
    public Boolean updateById(Dept dept) {
        return deptDao.updateById(dept);
    }

    @Override
    public Boolean insert(Dept dept) {
        return deptDao.insert(dept);
    }

    @Override
    public Boolean isNameExist(String name) {
        return deptDao.isNameExist(name);
    }

    @Override
    public List<Dept> getAllDepts() {
        return deptDao.getAllDepts();
    }

    @Override
    public int getPageCount(int pageSize) {
        int totalCount = deptDao.getTotalCount();
        int pageCount = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            pageCount += 1;
        }
        return pageCount;
    }

    @Override
    public List<Dept> getDepts(int pageNum, int pageSize) {
        return deptDao.getDepts((pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public String getDeptNameByPlaceId(Integer placeId) {
        return deptDao.getDeptNameByPlaceId(placeId);
    }

    @Override
    public List<Dept> getDeptBykw(String kw) {
        return deptDao.getDeptBykw(kw);
    }

    /*生成部门信息*/
    public void generateDept() {
        Dept dept = new Dept("技术部门");
        Dept dept1 = new Dept("产品部门");
        Dept dept2 = new Dept("运营部门");

        this.insert(dept);
        this.insert(dept1);
        this.insert(dept2);
    }
}
