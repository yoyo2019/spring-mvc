package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebErrorController {

    @RequestMapping("web_error")
    public String error(){
        return "error";
    }
}
