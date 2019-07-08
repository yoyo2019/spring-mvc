package com.test.controller;

import com.test.entity.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Api(description = "Demo API接口")
@Controller
@RequestMapping("/demo")
public class DemoController {

    @ApiOperation("全局异常测试接口")
    @GetMapping("/index")
    public String index(){
        //此处会通过全局异常拦截
//        int s = 1/0;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        return "hello";
    }

    @GetMapping("/person")
    public String toPerson(String name,double age){
        System.out.println(name+" "+age);
        return "hello";
    }

    @GetMapping("/person3")
    public String toPerson(@RequestParam(value = "ss") String name){
        System.out.println(name);
        return "hello";
    }

    @ApiOperation("参数校验接口")
    @GetMapping("/person2")
    public String toPerson(@Valid Person person){
        System.out.println(person.getName()+" "+person.getAge());
        return "hello";
    }

}
