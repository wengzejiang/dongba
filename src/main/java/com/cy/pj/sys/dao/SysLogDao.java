package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Package: com.cy.pj.sys.dao
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/20 0020 22:50
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Mapper
public interface SysLogDao {


    int getRowCount(@Param("username")String username);

    List<SysLog> findPageObjects( @Param("username") String username,@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    int deleteObjects(@Param("ids")Integer... ids);

    int insertObject(SysLog entity);
}
