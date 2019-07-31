package com.test.db.manager;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 切换数据源(不同方法调用不同数据源)
 * 注解式事务，和AOP数据源切面有一个顺序的关系
 * 数据源切换必须先执行，数据库事务才能获取到正确的数据源
 * 所以要明确指定 注解式事务和 我们AOP数据源切面的先后顺序
 */
@Aspect
@Component
@Order(1) //请注意：这里order一定要小于tx:annotation-driven的order，即先执行DataSourceAspect切面，再执行事务切面，才能获取到最终的数据源
@Slf4j
public class DataSourceAspect {

    /**
     * 配置前置通知,使用在方法aspect()上注册的切入点
     */
    /**
     *  execution (* com.sample.service.impl..*.*(..))
     *
     *  1、execution(): 表达式主体。
     *
     *  2、第一个*号：表示返回类型，*号表示所有的类型。
     *
     *  3、包名：表示需要拦截的包名，后面的两个点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。
     *
     *  4、第二个*号：表示类名，*号表示所有的类。
     *
     *  5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
     *
     * */
    @Before("@annotation(com.test.db.manager.DataSource)")
    public void before(JoinPoint point) {
        Class<?> className = point.getTarget().getClass();
        DataSource dataSource = null;
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        if(methodName!=null){
            //得到方法的参数的类型
            Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
            try {
                Method method = className.getMethod(methodName, argClass);
                if (method.isAnnotationPresent(DataSource.class)) {
                    dataSource = method.getAnnotation(DataSource.class);
                    log.info("DataSource Method Aop ====> "+dataSource.value());
                    DataSourceHolder.setDataSource(dataSource.value());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(className.getAnnotation(DataSource.class)!=null){
            //获取类上面DataSource注解
            dataSource = className.getAnnotation(DataSource.class);
            log.info("DataSource Class Aop ====> "+dataSource.value());
            DataSourceHolder.setDataSource(dataSource.value());
        }
    }

}
