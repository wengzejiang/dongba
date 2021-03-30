package com.cy.pj.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Package: com.cy.pj.common.aspect
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/30 0030 23:44
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Aspect
@Component
@Slf4j
public class SysExceptionAspect {
    //异常通知
    @AfterThrowing(value = "bean(*ServiceImpl)",throwing ="e")
    public void doHandleException(JoinPoint jp,Exception e){
        String className = jp.getTarget().getClass().getName();
        //方法签名(此对象中封装了要调用的目标方法信息，例如方法名，参数类型，返回值...)
        MethodSignature ms=(MethodSignature)jp.getSignature();
        log.error("{}'exception msg is {}",className+"."+ms.getName(),e.getMessage());
    }
}
