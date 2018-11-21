package com.cobcap.wageManager.controller;


import com.cobcap.wageManager.pojo.Dept;
import com.cobcap.wageManager.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    private static int pageSize = 10;

    @Autowired
    private DeptService deptService;

    /*根据请求路径参数num获取指定页数的dept数据*/
    @RequestMapping(value = {"/page", "/page/{num}"})
    public List<Dept> getDepts(@PathVariable(required = false) Integer num) {
        if(num == null)
            num = 1;
        return deptService.getDepts(num, pageSize);
    }

    /*获取总的person数据页数*/
    @RequestMapping("/pageCount")
    public int getPageCount() {
        return deptService.getPageCount(pageSize);
    }

    /*指定dept的id来更新dept的信息*/
    @RequestMapping("/update")
    public Boolean updateDept(@RequestBody Dept dept) {
        return deptService.updateById(dept);
    }

    /*删除指定id的dept*/
    @RequestMapping("/delete/{id}")
    public Boolean deleteDept(@PathVariable Integer id) {
        return deptService.deleteById(id);
    }
}
