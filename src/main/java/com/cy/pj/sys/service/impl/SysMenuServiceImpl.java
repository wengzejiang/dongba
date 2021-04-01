package com.cy.pj.sys.service.impl;

import com.cy.pj.common.annotation.RequiredCache;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Package: com.cy.pj.sys.service.impl
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/24 0024 22:19
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Override
    public List<Map<String, Object>> findObjects() {
        List<Map<String, Object>> objects = sysMenuDao.findObjects();
        if(objects==null|| objects.size()==0){
            throw new ServiceException("没有对应的菜单信息");
        }
        return objects;
    }


    @Override
    public int deleteObject(Integer id) {
        if(id==null||id<1){
            throw new IllegalArgumentException("id值无效");
        }
        int childCount = sysMenuDao.getChildCount(id);
        if(childCount>0){
            throw new ServiceException("请先删除子元素");
        }
        sysRoleMenuDao.deleteObjectsByMenuId(id);
        int rows = sysMenuDao.deleteObject(id);
        if(rows==0){
            throw new ServiceException("记录可能不存在");
        }
        return rows;
    }

    @RequiredCache
    @Override
    public List<Node> findZtreeMenuNodes() {
        return sysMenuDao.findZtreeMenuNodes();
    }

    @Override
    public int insertObject(SysMenu sysMenu) {
        if(sysMenu==null){
            throw new ServiceException("保存对象不能为空");
        }
        if(StringUtils.isEmpty(sysMenu.getName())){
            throw new ServiceException("菜单名不允许为空");
        }
        if(StringUtils.isEmpty(sysMenu.getPermission())){
            throw new ServiceException("授权标识不允许为空");
        }
        return sysMenuDao.insertObject(sysMenu);
    }

    @Override
    public int updateObject(SysMenu entity) {
        //1.合法验证
        if(entity==null)
            throw new ServiceException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new ServiceException("菜单名不能为空");
        //2.更新数据
        int rows=sysMenuDao.updateObject(entity);
        if(rows==0)
            throw new ServiceException("记录可能已经不存在");
        return rows;
    }
}
