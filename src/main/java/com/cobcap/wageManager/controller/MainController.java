package com.cobcap.wageManager.controller;

import com.cobcap.wageManager.pojo.Dept;
import com.cobcap.wageManager.service.DeptService;
import com.cobcap.wageManager.service.PersonService;
import com.cobcap.wageManager.service.PlaceService;
import com.cobcap.wageManager.vo.PersonVo;
import com.cobcap.wageManager.vo.PlaceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PersonService personService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private PlaceService placeService;

    private static int pageSize = 10;

    /*网站主页*/
    @RequestMapping("/")
    public String index() {
        return "login";
    }

    /*用户登录*/
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
            mv.addObject("person", personService.transFormData(personService.getById(Integer.valueOf(num))));
            mv.setViewName("person");
            return mv;
        }


        mv.setViewName("404");
        return mv;

    }


    /*查询人员的关键词*/
    @RequestMapping("/search/person/{kw}")
    @ResponseBody
    public List<PersonVo> searchByPersonKw(@PathVariable String kw) {
        return personService.transFormData(personService.getPersonBykw(kw));
    }

    /*查询部门的关键词*/
    @RequestMapping("/search/dept/{kw}")
    @ResponseBody
    public List<Dept> searchByDeptKw(@PathVariable String kw) {
        return deptService.getDeptBykw(kw);
    }

    /*查询职位的关键词*/
    @RequestMapping("/search/place/{kw}")
    @ResponseBody
    public List<PlaceVo> searchByPlaceKw(@PathVariable String kw) {
        return placeService.transFormData(placeService.getPlaceBykw(kw));
    }

    /*退出登录*/
    @RequestMapping("/signout")
    public String backToLogin(HttpSession session) {
        /*清除session*/
        session.invalidate();
        return "redirect:/";
    }

}
