package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.dao.DeptDao;
import com.cobcap.wageManager.pojo.Dept;
import com.cobcap.wageManager.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public Dept getById(Integer id) {
        return deptDao.getById(id);
    }

    @Override
    public Boolean deleteById(Integer id) {
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
}
