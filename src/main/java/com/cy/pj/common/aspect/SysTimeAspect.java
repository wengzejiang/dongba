package com.cy.pj.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Package: com.cy.pj.common.aspect
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/30 0030 23:25
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Component
@Aspect
public class SysTimeAspect {

    @Pointcut("bean(sysMenuServiceImpl)")
    public void doTime(){}

    @Before("doTime()")
    public void doBefore(JoinPoint jp){
        System.out.println("time doBefore()");
    }
    @After("doTime()")
    public void doAfter(){
        System.out.println("time doAfter()");
    }
    /**核心业务正常结束时执行
     * 说明：假如有after，先执行after,再执行returning*/
    @AfterReturning("doTime()")
    public void doAfterReturning(){
        System.out.println("time doAfterReturning");
    }
    /**核心业务出现异常时执行
     说明：假如有after，先执行after,再执行Throwing*/
    @AfterThrowing("doTime()")
    public void doAfterThrowing(){
        System.out.println("time doAfterThrowing");
    }
    @Around("doTime()")
    public Object doAround(ProceedingJoinPoint jp)
            throws Throwable{
        System.out.println("doAround.before");
        Object obj=jp.proceed();
        System.out.println("doAround.after");
        return obj;
    }
}
