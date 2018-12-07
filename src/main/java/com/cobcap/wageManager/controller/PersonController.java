package com.cobcap.wageManager.controller;


import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.service.PersonService;
import com.cobcap.wageManager.vo.PersonVo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
public class PersonController {

    private static int pageSize = 10;

    @Autowired
    private PersonService personService;

    /*根据请求路径参数num获取指定页数的person数据*/
    @RequestMapping(value = {"/page", "/page/{num}"})
    public List<PersonVo> getPersons (@PathVariable(required = false) Integer num) {
        if(num == null)
            num = 1;
        return personService.getPersonVos(num, pageSize);
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


    /*检验编号和密码是否正确*/
    @RequestMapping("/check")
    public Boolean checkNumAndPass(@RequestParam("num") String num,
                                   @RequestParam("pass") String pass, HttpSession session) {

        Boolean flag = false;

        /*管理员账号*/
        if (num.equals("0000") && pass.equals("1111")) {
            flag = true;
        }else {
            /*普通账号*/
            flag = personService.isPassRight(Integer.valueOf(num), pass);
        }


        if (flag) {
            session.setAttribute("num", num);
        }

        return flag;
    }

    /*添加person*/
    @RequestMapping("/add")
    public Boolean addPerson(@RequestBody Person person) {
        return personService.addUser(person);
    }


    @RequestMapping("/name/{id}")
    public Map getName(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", personService.getNameById(id));
        return map;
    }
}
