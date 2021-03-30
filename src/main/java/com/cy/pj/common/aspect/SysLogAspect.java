package com.cy.pj.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Package: com.cy.pj.common.aspect
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/29 0029 22:43
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
//@Aspect注解描述的类型，为AOP中的一种切面类型，在这种切面类型中通常要
//切入点pointcut:进行功能增强的位置
//通知advice:功能增强
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    //方法实现不需要写任何内容
    //pointCut用于定义切入点
    //bean(sysUserServiceImpl)为切入点表达式
    //在当前应用中，所有的方法的集合为切入点(这个切入点中任意的一个方法执行时，都要进行功能扩展)
    @Pointcut("bean(sysUserServiceImpl)")
    public void doPointcut(){ }

    //@Around环绕通知用于描述通知（Advice),切面中的方法除了切入点都是通知,通知中要实现你要扩展的功能
    //jp连接点,@Around描述的方法中，参数类型要求为ProceedingJoinPoint
    //return 目标业务方法的返回值，对于@Around描述的方法，其返回值类型要求为Object
    @Around("doPointcut()")//描述doPointcut（）方法的注解中内容为切入点表达式
    //@Around("bean(sysUserServiceImpl)")
    public Object logAround(ProceedingJoinPoint jp) throws Throwable{
        log.info("start {}:"+System.currentTimeMillis());
        try{
            Object result = jp.proceed();//调用下一个切面或本类中其他通知，或目标方法
            log.info("end {}:"+System.currentTimeMillis());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            log.error("method error{}"+System.currentTimeMillis());
            throw e;
        }
    }
}
