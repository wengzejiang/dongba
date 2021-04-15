package com.cy.pj.common.utils;

import com.cy.pj.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * Package: com.cy.pj.common.utils
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/4/13 0013 22:43
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
public class ShiroUtils {

    public static String getUserName(){
        return getUser().getUsername();
    }

    public static SysUser getUser(){
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }
}
