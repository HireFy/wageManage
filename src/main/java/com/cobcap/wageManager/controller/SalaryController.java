package com.cobcap.wageManager.controller;

import com.cobcap.wageManager.service.SalaryService;
import com.cobcap.wageManager.vo.SalaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    private static int pageSize = 10;

    @Autowired
    private SalaryService salaryService;

    @RequestMapping(value = {"/page", "/page/{num}"})
    public List<SalaryVo> getBonuses(@PathVariable(required = false) Integer num) {
        if(num == null)
            num = 1;
        return salaryService.getSalaryVOs(num, pageSize);
    }

    @RequestMapping("/pageCount")
    public int getPageCount() {
        return salaryService.getPageCount(pageSize);
    }
//
//    @RequestMapping("/delete/{id}")
//    public Boolean deleteById(@PathVariable Integer id) {
//        return salaryService.delete(id);
//    }
//
//    @RequestMapping("/update")
//    public Boolean update(@RequestBody Salary salary) {
//        return salaryService.update(bonus);
//    }
}
