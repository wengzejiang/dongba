package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

/**
 * Package: com.cy.pj.sys.dao
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/26 0026 22:08
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Mapper
public interface SysUserRoleDao {

    @Delete("delete from sys_user_roles where role_id=#{roleId}")
    int deleteObjectsByRoleId(Integer roleId);
}
