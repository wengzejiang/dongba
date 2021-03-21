package com.cy.pj.common.web;

import com.cy.pj.common.vo.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Package: com.cy.pj.common.web
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/21 0021 14:24
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */

//描述的类为全局异常处理类
//控制层出现异常以后，可以由此进行处理
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)//描述的方法为异常处理方法
    @ResponseBody
    public JsonResult doHandleRuntimeException(RuntimeException e){
        e.printStackTrace();
        return new JsonResult(e);
    }
}
