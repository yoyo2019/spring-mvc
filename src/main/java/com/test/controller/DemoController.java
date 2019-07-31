package com.test.controller;

import com.test.db.manager.DataSource;
import com.test.entity.Person;
import com.test.entity.UserTest;
import com.test.mapper.UserTestMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Api(description = "Demo API接口")
@Controller
@RequestMapping("/demo")
@Validated
public class DemoController {

    @Resource
    UserTestMapper userTestMapper;

    @ApiOperation("全局异常测试接口")
    @GetMapping("/index")
    public String index(){
        //此处会通过全局异常拦截
//        int s = 1/0;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        return "hello";
    }

    @DataSource("two")
    @ApiOperation("测试")
    @GetMapping("/person4")
    public String toPerson(){
        System.out.println("ssssssss");


        List<UserTest> list = userTestMapper.selectList(null);
        System.out.println(list.size());

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
