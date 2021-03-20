package com.cy.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @RequestMapping("doIndexUI")
    public String doIndexUI(){
        return "starter";
    }


    @RequestMapping("log/log_list")
    public String doLogUI(){
        return "sys/log_list";
    }

    @RequestMapping("doPageUI")
    public String doPageUI(){
        return "common/page";
    }

}
