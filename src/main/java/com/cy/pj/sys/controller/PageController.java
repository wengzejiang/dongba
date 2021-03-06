package com.cy.pj.sys.controller;

import com.cy.pj.common.utils.ShiroUtils;
import com.cy.pj.common.vo.SysUserMenuVo;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Package: com.cy.pj.sys.controller
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/20 0020 15:31
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Controller
@RequestMapping("/")
public class PageController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("doIndexUI")
    public String doIndexUI(Model model){//model对象为springmvc模块设计的用于封装响应数据的一个对象
        SysUser user=ShiroUtils.getUser();
        model.addAttribute("username", user.getUsername());//底层会将数据存储到请求作用域
        List<SysUserMenuVo> userMenus= sysUserService.findUserMenusByUserId(user.getId());
        model.addAttribute("userMenus",userMenus);
        return "starter";
    }

    @RequestMapping("doLoginUI")
    public String doLoginUI(){
        return "login";
    }


//    @RequestMapping("log/log_list")
//    public String doLogUI(){
//        return "sys/log_list";
//    }

    @RequestMapping("doPageUI")
    public String doPageUI(){
        return "common/page";
    }

//    @RequestMapping("menu/menu_list")
//    public String doMenuUI(){
//        return "sys/menu_list";
//    }
    //rest风格的一种url定义,语法{url}
    //@PathVariable  注解描述的方法参数，表示他的值从url路径中获取(和参数名相同的的url变量值)
    @RequestMapping("{module}/{moduleUI}")
    public String doModule(@PathVariable String moduleUI){
        return "sys/"+moduleUI;
    }



}
