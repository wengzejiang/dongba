package com.cy.pj.sys.service.impl;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
