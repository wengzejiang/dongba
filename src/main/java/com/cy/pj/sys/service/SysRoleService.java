package com.cy.pj.sys.service;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.common.vo.SysRoleMenuVo;
import com.cy.pj.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Package: com.cy.pj.sys.service
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/25 0025 22:52
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
public interface SysRoleService {

    int deleteObject(Integer id);

    PageObject<SysRole> findPageObjects(String name,Integer pageCurrent);

    int insertObjects(SysRole entity,Integer... menuIds);

    SysRoleMenuVo findObjectById(Integer id) ;

    int updateObject(SysRole entity,Integer[] menuIds);

    List<CheckBox> findObjects();
}
