package com.cy.pj.sys.dao;

import com.cy.pj.common.vo.SysUserDeptVo;
import com.cy.pj.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Package: com.cy.pj.sys.dao
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/27 0027 14:34
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Mapper
public interface SysUserDao {
    int getRowCount(@Param("username") String username);

    List<SysUserDeptVo> findPageObjects(@Param("username")String  username, @Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize);

    int validById(@Param("id") Integer id,@Param("valid") Integer valid,@Param("modifiedUser")String modifiedUser);

    int insertObject(SysUser entity);

    SysUserDeptVo findObjectById(Integer id);

    int updateObject(SysUser entity);

    SysUser findUserByUserName(String username);

    int updatePassword(@Param("password") String password,@Param("salt") String salt,@Param("id")Integer id);
}
