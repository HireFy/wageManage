package com.cobcap.wageManager.controller;


import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private static int pageSize = 10;

    @Autowired
    private PersonService personService;

    @RequestMapping(value = {"/page", "/page/{num}"})
    public List<Person> getPersons (@PathVariable(required = false) Integer num) {
        if(num == null)
            num = 1;
        return personService.getPersons(num, pageSize);
    }


    @RequestMapping("/pageCount")
    public int getPageCount() {
        return personService.getPageCount(pageSize);
    }
}
