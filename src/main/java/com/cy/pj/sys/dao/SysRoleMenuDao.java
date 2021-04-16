package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Package: com.cy.pj.sys.dao
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/24 0024 22:57
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Mapper
public interface SysRoleMenuDao {

    @Delete("delete from sys_role_menus where menu_id=#{id}")
    int deleteObjectsByMenuId(@Param("id") Integer id);

    @Delete("delete from sys_role_menus where role_id=#{roleId}")
    int deleteObjectsByRoleId(Integer roleId);

    int insertObjects(@Param("roleId") Integer roleId,@Param("menuIds") Integer... menuIds);

    @Select("select menu_id from sys_role_menus where role_id=#{roleId}")
    List<Integer> findMenuIdsByRoleId(Integer roleId);

    List<Integer> findMenuIdsByRoleIds(@Param("roleIds") Integer... roleIds);
}
