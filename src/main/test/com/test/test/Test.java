package com.test.test;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.entity.TmpUser;
import com.test.entity.User;
import com.test.mapper.TmpUserMapper;
import com.test.mapper.UserMapper;
import com.test.service.TmpUserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//不加WebAppConfiguration时Junit下启动Swagger会报错
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class Test{

    @Value("${jdbc.user}")
    private String value;

    @org.junit.Test
    public void ss(){

        System.out.println(1);
//        Assert.assertEquals(1,2);
        long a = 60000;

        a = System.currentTimeMillis();

        System.out.println(Convert.convertTime(a, TimeUnit.MILLISECONDS, TimeUnit.DAYS));
        System.out.println(Convert.toDate(a));
        System.out.println(2);

        Properties prop = Props.getProp("log4j.properties");
        System.out.println(prop.get("log4j.rootLogger"));

        String str = "abcdefgh";
        String strSub1 = StrUtil.sub(str, 2, 3); //strSub1 -> c
        String strSub2 = StrUtil.sub(str, 2, -3); //strSub2 -> cde
        String strSub3 = StrUtil.sub(str, 3, 2); //strSub2 -> c

        String template = "{}爱{}，就像老鼠爱大米";
        String str2 = StrUtil.format(template, "我", "你"); //str -> 我爱你，就像老鼠爱大米

        Method[] methods = ReflectUtil.getMethods(Test.class);
        ReflectUtil.newInstance(Test.class);
        for(Method me : methods){
            System.out.println(me.getName());
        }

        Console.log("This is Console log for {}.", "test");

        System.out.println(value);

    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TmpUserMapper tmpUserMapper;
    @Autowired
    private TmpUserService tmpUserService;

    @org.junit.Test
    public void testSelect() {

        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        for(User u : userList){
            System.out.println(u.getName()+" , "+u.getTmpMk());
        }

        userMapper.insert(new User().setName("xxxx"));

        Map<String,Object> columnMap = new HashMap<String, Object>();
        columnMap.put("tmp_mk","Jone");
        userMapper.deleteByMap(columnMap);
        userMapper.delete(new QueryWrapper<User>().lambda().lt(User::getTmpMk,2));
        userMapper.deleteById(5);

        userMapper.update(
                new User().setEmail("miemie@baomidou.com"),
                new QueryWrapper<User>()
                        .lambda().eq(User::getTmpMk, 2));


    }

    @org.junit.Test
    public void testTmpUser(){
        TmpUser ts = new TmpUser();

        Page<TmpUser> page = new Page(1,5);
        IPage<TmpUser> userIPage = tmpUserMapper.selectPage(page,null);
        System.out.println(userIPage.getTotal());

//        tmpUserMapper.selectList(null);
//
//        User tt = new User();
//        tt.setName("bb");
//        userMapper.insert(tt);
//        System.out.println(tt.getId());
    }
}
