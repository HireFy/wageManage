package com.cobcap.wageManager.controller;


import com.cobcap.wageManager.pojo.Dept;
import com.cobcap.wageManager.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    private static int pageSize = 10;

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = {"/page", "/page/{num}"})
    public List<Dept> getDepts(@PathVariable(required = false) Integer num) {
        if(num == null)
            num = 1;
        return deptService.getDepts(num, pageSize);
    }

    @RequestMapping("/pageCount")
    public int getPageCount() {
        return deptService.getPageCount(pageSize);
    }
}
