package com.cy.pj.sys.controller;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Package: com.cy.pj.sys.controller
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/24 0024 22:22
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Controller
@RequestMapping("/menu/")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("doFindObjects")
    @ResponseBody
    public JsonResult doFindObjects(){
        List<Map<String, Object>> objects = sysMenuService.findObjects();
        return new JsonResult(objects);
    }

    @RequestMapping("doDeleteObject")
    @ResponseBody
    public JsonResult delete(Integer id) {
        int rows = sysMenuService.deleteObject(id);
        return new JsonResult("delete ok");
    }

    @RequestMapping("doFindZtreeMenuNodes")
    @ResponseBody
    public JsonResult doFindZtreeMenuNodes(){
        return new JsonResult(sysMenuService.findZtreeMenuNodes());
    }

    @RequestMapping("doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(SysMenu entity){
        sysMenuService.insertObject(entity);
        return new JsonResult("save ok");
    }
}
