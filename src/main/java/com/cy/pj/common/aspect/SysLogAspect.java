package com.cy.pj.common.aspect;

import com.cy.pj.common.annotation.RequestLog;
import com.cy.pj.common.utils.IPUtils;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

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
    //@Pointcut("bean(sysUserServiceImpl)")
    @Pointcut("@annotation(com.cy.pj.common.annotation.RequestLog)")
    public void doPointcut(){ }

    //@Around环绕通知用于描述通知（Advice),切面中的方法除了切入点都是通知,通知中要实现你要扩展的功能
    //jp连接点,@Around描述的方法中，参数类型要求为ProceedingJoinPoint
    //return 目标业务方法的返回值，对于@Around描述的方法，其返回值类型要求为Object
    @Around("doPointcut()")//描述doPointcut（）方法的注解中内容为切入点表达式
    //@Around("bean(sysUserServiceImpl)")
    public Object logAround(ProceedingJoinPoint jp) throws Throwable{
        long start=System.currentTimeMillis();
        log.info("start {}:"+start);
        try{
            Object result = jp.proceed();//调用下一个切面或本类中其他通知，或目标方法
            long end=System.currentTimeMillis();
            log.info("end {}:"+end);
            saveLog(jp,(end-start));
            return result;
        }catch (Exception e){
            e.printStackTrace();
            log.error("method error{}"+System.currentTimeMillis());
            throw e;
        }
    }

    @Autowired
    private SysLogService sysLogService;

    private void saveLog(ProceedingJoinPoint jp, long time) throws NoSuchMethodException, SecurityException, JsonProcessingException {
        //1.获取日志信息
        MethodSignature ms= (MethodSignature)jp.getSignature();
        Class<?> targetClass=jp.getTarget().getClass();
        String className=targetClass.getName();
        //获取接口声明的方法
        String methodName=ms.getMethod().getName();
        Class<?>[] parameterTypes=ms.getMethod().getParameterTypes();
        //获取目标对象方法(AOP版本不同,可能获取方法对象方式也不同)
        Method targetMethod=targetClass.getDeclaredMethod(
                methodName,parameterTypes);
        //获取用户名,学完shiro再进行自定义实现,没有就先给固定值
        //String username=ShiroUtils.getPrincipal().getUsername();
        //获取方法参数
        Object[] paramsObj=jp.getArgs();
        System.out.println("paramsObj="+paramsObj);
        //将参数转换为字符串
        String params=new ObjectMapper().writeValueAsString(paramsObj);
        //2.封装日志信息
        SysLog log=new SysLog();
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        log.setUsername(user.getUsername());//登陆的用户
        //假如目标方法对象上有注解,我们获取注解定义的操作值
        RequestLog requestLog=
                targetMethod.getDeclaredAnnotation(RequestLog.class);
        if(requestLog!=null){
            log.setOperation(requestLog.value());
        }
        log.setMethod(className+"."+methodName);//className.methodName()
        log.setParams(params);//method params
        log.setIp(IPUtils.getIpAddr());//ip 地址
        log.setTime(time);//
        log.setCreatedTime(new Date());
        //3.保存日志信息
        sysLogService.saveObject(log);

    }
}
