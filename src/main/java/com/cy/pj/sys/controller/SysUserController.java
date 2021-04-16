package com.cy.pj.sys.controller;

import com.cy.pj.common.utils.ShiroUtils;
import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Package: com.cy.pj.sys.controller
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/27 0027 15:38
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@RestController
@RequestMapping("/user/")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("doFindPageObjects")
    public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
        return new JsonResult(
                sysUserService.findPageObjects(username,pageCurrent));
    }

    @RequestMapping("doValidById")
    @ResponseBody
    public JsonResult doValidById(Integer id,Integer valid){
        sysUserService.validById(id,valid, ShiroUtils.getUserName());//"admin"用户将来是登陆用户
        return new JsonResult("update ok");
    }

    @RequestMapping("doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(SysUser entity, Integer[] roleIds){
        sysUserService.saveObject(entity,roleIds);
        return new JsonResult("save ok");
    }

    @RequestMapping("doFindObjectById")
    @ResponseBody
    public JsonResult doFindObjectById(Integer id){
        Map<String,Object> map=sysUserService.findObjectById(id);
        return new JsonResult(map);
    }

    @RequestMapping("doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(
            SysUser entity,Integer[] roleIds){
        sysUserService.updateObject(entity,roleIds);
        return new JsonResult("update ok");
    }

    @RequestMapping("doLogin")
    @ResponseBody
    public JsonResult doLogin(String username,String password,boolean isRememberMe){
        //1.获取Subject对象
        Subject subject= SecurityUtils.getSubject();
        //2.通过Subject提交用户信息,交给shiro框架进行认证操作
        //2.1对用户进行封装
        UsernamePasswordToken token= new UsernamePasswordToken(
                        username,//身份信息
                        password);//凭证信息
        if(isRememberMe) {
            //告诉SecurityManager要记住登陆用户
            token.setRememberMe(true);
        }
        //2.2对用户信息进行身份认证
        subject.login(token);
        //分析:
        //1)token会传给shiro的SecurityManager
        //2)SecurityManager将token传递给认证管理器
        //3)认证管理器会将token传递给realm
        return new JsonResult("login ok");
    }

    @RequestMapping("doUpdatePassword")
    @ResponseBody
    public JsonResult doUpdatePassword(String pwd,String newPwd,String cfgPwd){
        sysUserService.updatePassword(pwd,newPwd,cfgPwd);
        return new JsonResult("update ok");
    }
}

