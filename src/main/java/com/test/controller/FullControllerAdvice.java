package com.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class FullControllerAdvice {

//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        return new MethodValidationPostProcessor();
//    }

    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception ex){

        if(ex instanceof ConstraintViolationException){
            List<String> msgList = new ArrayList<>();
            for (ConstraintViolation<?> constraintViolation : ((ConstraintViolationException) ex).getConstraintViolations()) {
                msgList.add(constraintViolation.getMessage());
            }
            log.info("bad request2, " + msgList);
        }

        if(ex instanceof BindException){
            BindingResult bindingResult = ((BindException) ex).getBindingResult();
            String errorMesssage = "校验失败:";
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMesssage += fieldError.getDefaultMessage() + ", ";
            }
            log.info("bad request2, " + errorMesssage);
        }

        ex.printStackTrace();
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", ex);
        System.out.println("in FullControllerAdvice");
        return mv;
    }
}
