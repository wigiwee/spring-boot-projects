package com.elearn.app.elearn_bak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller("/")
public class PageController {

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }
    
}
