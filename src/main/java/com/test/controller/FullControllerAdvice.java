package com.test.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class FullControllerAdvice {

    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception ex){
        ex.printStackTrace();
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", ex);
        System.out.println("in FullControllerAdvice");
        return mv;
    }
}
