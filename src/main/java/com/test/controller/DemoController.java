package com.test.controller;

import com.test.entity.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Api(description = "Demo API接口")
@Controller
@RequestMapping("/demo")
@Validated
public class DemoController {

    @ApiOperation("全局异常测试接口")
    @GetMapping("/index")
    public String index(){
        //此处会通过全局异常拦截
//        int s = 1/0;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        return "hello";
    }

    @ApiOperation("测试")
    @GetMapping("/person4")
    public String toPerson(){
        System.out.println("ssssssss");
        return "hello";
    }

    @ApiOperation("参数校验接口")
    @GetMapping("/person")
    public String toPerson(@Valid Person person){
        System.out.println(person.getName()+" "+person.getAge());
        return "hello";
    }

    @GetMapping("/person2")
    public String toPerson(@NotNull(message = "xxx不允许为空") String vStr){
        System.out.println(vStr);
        return "hello";
    }

}
