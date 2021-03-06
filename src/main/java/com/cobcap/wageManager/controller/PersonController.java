package com.cobcap.wageManager.controller;


import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private static int pageSize = 10;

    @Autowired
    private PersonService personService;

    /*根据请求路径参数num获取指定页数的person数据*/
    @RequestMapping(value = {"/page", "/page/{num}"})
    public List<Person> getPersons (@PathVariable(required = false) Integer num) {
        if(num == null)
            num = 1;
        return personService.getPersons(num, pageSize);
    }

    /*获取总的person数据页数*/
    @RequestMapping("/pageCount")
    public int getPageCount() {
        return personService.getPageCount(pageSize);
    }

    /*指定person的id来更新person的信息*/
    @RequestMapping("/update")
    public Boolean updatePerson(@RequestBody Person person) {
        return personService.update(person);
    }

    /*删除指定id的person*/
    @RequestMapping("/delete/{id}")
    public Boolean deletePerson(@PathVariable Integer id) {
        return personService.deleteById(id);
    }

}
