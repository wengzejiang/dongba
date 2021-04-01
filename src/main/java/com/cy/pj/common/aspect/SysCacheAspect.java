package com.cy.pj.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: com.cy.pj.common.aspect
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/31 0031 22:03
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Component
@Aspect
public class SysCacheAspect {

    private Map<String,Object> cache=new HashMap<>();

    @Pointcut("@annotation(com.cy.pj.common.annotation.RequiredCache)")
    public void doCachePointCut(){
    }


    @Around("doCachePointCut()")
    public Object doCacheAroud(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("get data from cache");
        Object obj=cache.get("menuZTree");//假设key为menuZTree
        if(obj!=null){
            return obj;
        }
        Object result = jp.proceed();
        cache.put("menuZTree",result);
        System.out.println("put data from cache");
        return result;
    }
}
