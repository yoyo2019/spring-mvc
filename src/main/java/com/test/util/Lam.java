package com.test.util;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * getter/setter引用转换属性名的工具类
 * Lambda方法体表达式转换成下划线字符串
 */
public class Lam {

    private static final Logger log = LoggerFactory.getLogger(Lam.class);

    /**
     * 缓存类-Lambda的映射关系
     */
    private static Map<Class, SerializedLambda> CLASS_LAMDBA_CACHE = new ConcurrentHashMap<>();

    /***
     * 转换方法引用为属性名 下划线形式
     * @param fn
     * @return
     */
    public static <T> String toStr(IGetter<T> fn) {
        SerializedLambda lambda = getSerializedLambda(fn);
        String methodName = lambda.getImplMethodName();
        String prefix = null;
        if(methodName.startsWith("get")){
            prefix = "get";
        }
        else if(methodName.startsWith("is")){
            prefix = "is";
        }
        if(prefix == null){
            log.warn("无效的getter方法: "+methodName);
        }
        // 截取get/is之后的字符串并转换首字母为小写
        return StrUtil.toUnderlineCase(StrUtil.getGeneralField(methodName));
    }

    /***
     * 转换setter方法引用为属性名
     * @param fn
     * @return
     */
    public static <T,R> String toStr(ISetter<T,R> fn) {
        SerializedLambda lambda = getSerializedLambda(fn);
        String methodName = lambda.getImplMethodName();
        if(!methodName.startsWith("set")){
            log.warn("无效的setter方法: "+methodName);
        }
        // 截取set之后的字符串并转换首字母为小写
        return StrUtil.toUnderlineCase(StrUtil.getGeneralField(methodName));
    }

    /***
     * 获取类对应的Lambda
     * @param fn
     * @return
     */
    private static SerializedLambda getSerializedLambda(Serializable fn){
        //先检查缓存中是否已存在
        SerializedLambda lambda = CLASS_LAMDBA_CACHE.get(fn.getClass());
        if(lambda == null){
            try{//提取SerializedLambda并缓存
                Method method = fn.getClass().getDeclaredMethod("writeReplace");
                method.setAccessible(Boolean.TRUE);
                lambda = (SerializedLambda) method.invoke(fn);
                CLASS_LAMDBA_CACHE.put(fn.getClass(), lambda);
            }
            catch (Exception e){
                log.error("获取SerializedLambda异常, class="+fn.getClass().getSimpleName(), e);
            }
        }
        return lambda;
    }

    @FunctionalInterface
    public interface IGetter<T> extends Serializable {

        Object apply(T source);

    }

    @FunctionalInterface
    public interface ISetter<T,U> extends Serializable {

        void accept(T t, U u);

    }
}


