package com.cy.pj.sys.controller;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Package: com.cy.pj.sys.controller
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/25 0025 23:05
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Controller
@RequestMapping("/role/")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("doFindPageObjects")
    @ResponseBody
    public JsonResult doFindPageObjects(String name,Integer pageCurrent) {
        return new JsonResult(sysRoleService.findPageObjects(name,pageCurrent));
    }

    @ResponseBody
    @RequestMapping("doDeleteObject")
    public JsonResult doDeleteObject(int id){
        sysRoleService.deleteObject(id);
        return new JsonResult("delete ok");
    }

    @RequestMapping("doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(
            SysRole entity, Integer[] menuIds){
        sysRoleService.insertObjects(entity,menuIds);
        return new JsonResult("save ok");
    }

}
