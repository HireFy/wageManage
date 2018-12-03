package com.cobcap.wageManager.controller;

import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.service.PersonService;
import com.cobcap.wageManager.vo.PersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private PersonService service;



    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping("/person/{number}")
    public ModelAndView login(@PathVariable String number, HttpSession session) {

        ModelAndView mv = new ModelAndView();

        String num = (String) session.getAttribute("num");

        /*如果地址栏中的值和session中的编号值不一样的话跳转404*/
        if (!num.equals(number)) {
            mv.setViewName("404");
            return mv;
        }

        /*管理员账号密码*/
        if (num.equals("0000")) {
            mv.setViewName("index");
            return mv;
        }

        /*普通用户账号密码*/
        if (num != null) {
            mv.addObject("person", service.transFormData(service.getById(Integer.valueOf(num))));
            mv.setViewName("person");
            return mv;
        }


        mv.setViewName("404");
        return mv;

    }

    @RequestMapping("/search/name/{name}")
    @ResponseBody
    public PersonVo searchByName(@PathVariable String name) {
        return service.transFormData(service.getPersonByName(name));
    }
}
