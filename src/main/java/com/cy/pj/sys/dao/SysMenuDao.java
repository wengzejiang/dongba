package com.cy.pj.sys.dao;

import com.cy.pj.common.vo.Node;
import com.cy.pj.common.vo.SysUserMenuVo;
import com.cy.pj.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Package: com.cy.pj.sys.dao
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/23 0023 22:58
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Mapper
public interface SysMenuDao {

    List<Map<String, Object>> findObjects();

    int getChildCount(@Param("id") Integer id);

    int deleteObject(@Param("id")Integer id);

    List<Node> findZtreeMenuNodes();

    int insertObject(SysMenu sysMenu);

    int updateObject(SysMenu entity);

    List<String> findPermissions(@Param("menuIds")Integer...menuIds);

    /**
     * 基于菜单获取菜单信息
     * @param menuIds
     * @return
     */
    List<SysUserMenuVo> findMenusByIds(@Param("menuIds")List<Integer> menuIds);

}
