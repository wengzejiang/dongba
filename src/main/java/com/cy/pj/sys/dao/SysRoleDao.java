package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Package: com.cy.pj.sys.dao
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/25 0025 22:48
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Mapper
public interface SysRoleDao {

    int getRowCount(@Param("name") String name);

    List<SysRole> findPageObjects(@Param("name") String name,@Param("startIndex") Integer startIndex,@Param("pageSize")Integer pageSize);
}