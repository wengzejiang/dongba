package com.cy.pj.sys.controller;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Package: com.cy.pj.sys.controller
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/21 0021 14:07
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */

@Controller
@RequestMapping("/log/")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @ResponseBody
    @RequestMapping("doFindPageObjects")
    public JsonResult doFindPageObject(String username,Integer pageCurrent){
        PageObject<SysLog> pageObjects = sysLogService.findPageObjects(username, pageCurrent);
        JsonResult jsonResult = new JsonResult(pageObjects);
        System.out.println(jsonResult);
        return jsonResult;
    }
}
