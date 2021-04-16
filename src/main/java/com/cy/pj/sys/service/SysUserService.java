package com.cy.pj.sys.service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.common.vo.SysUserDeptVo;
import com.cy.pj.common.vo.SysUserMenuVo;
import com.cy.pj.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Package: com.cy.pj.sys.service
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/27 0027 15:36
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
public interface SysUserService {
    PageObject<SysUserDeptVo> findPageObjects(
            String username,Integer pageCurrent);

    int validById(@Param("id") Integer id, @Param("valid") Integer valid, @Param("modifiedUser")String modifiedUser);

    int saveObject(SysUser entity, Integer[]roleIds);

    Map<String,Object> findObjectById(Integer userId) ;

    int updateObject(SysUser entity,Integer[] roleIds);

    int updatePassword(String sourcePassword,String newPassword,String cfgPassword);

    List<SysUserMenuVo> findUserMenusByUserId(Integer userId);

}
