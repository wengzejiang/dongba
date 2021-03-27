package com.cy.pj.sys.dao;

import com.cy.pj.common.vo.SysUserDeptVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

}
