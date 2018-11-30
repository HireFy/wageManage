package com.cobcap.wageManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("num") String num,
                        @RequestParam("pass") String pass) {
        if (num == "0000" && pass == "1111") {
            return "index";
        }

        return "index";
    }
}
