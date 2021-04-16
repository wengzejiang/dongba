package com.cy.pj.common.web;

import com.cy.pj.common.exception.ServiceException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * Package: com.cy.pj.common.web
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/4/15 0015 21:31
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
//SpringMvc中的拦截器
public class TimeAccessInterceptor implements HandlerInterceptor {

    //拦截器中的preHandle方法会在目标控制层方法执行之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取java中的日历对象
        Calendar c=Calendar.getInstance();
        //定义开始访问时间
        c.set(Calendar.HOUR_OF_DAY, 6);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        long start=c.getTimeInMillis();
        //定义终止访问时间
        c.set(Calendar.HOUR_OF_DAY,24);
        long end=c.getTimeInMillis();
        long current=System.currentTimeMillis();
        if(current<start||current>end){
            throw new ServiceException("不在访问时间之内");
        }
        return true;//false表示不放行
    }
}
