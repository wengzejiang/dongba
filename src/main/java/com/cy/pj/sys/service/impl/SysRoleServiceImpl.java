package com.cy.pj.sys.service.impl;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.common.vo.SysRoleMenuVo;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Package: com.cy.pj.sys.service.impl
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/25 0025 22:52
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Override
    public int deleteObject(Integer id) {
        if(id==null||id<1){
            throw new IllegalArgumentException("id值无效");
        }
        sysRoleMenuDao.deleteObjectsByRoleId(id);
        sysUserRoleDao.deleteObjectsByRoleId(id);
        int rows = sysRoleDao.deleteObject(id);
        if(rows==0){
            throw new ServiceException("记录不存在");
        }
        return rows;
    }

    @Override
    public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
        if(pageCurrent==null||pageCurrent<1){
            throw new IllegalArgumentException("当前页码值不对");
        }
        int rowCount = sysRoleDao.getRowCount(name);
        if(rowCount==0){
            throw new ServiceException("没有查询到对应的记录");
        }
        int pageSize=3;
        int start=(pageCurrent-1)*pageSize;
        List<SysRole> records = sysRoleDao.findPageObjects(name, start, pageSize);
        return new PageObject<>(rowCount,records,pageCurrent,pageSize);
    }

    @Override
    public int insertObjects(SysRole entity, Integer... menuIds) {
        if(entity==null){
            throw new IllegalArgumentException("保存对象不能为空");
        }
        if(StringUtils.isEmpty(entity.getName())){
            throw new IllegalArgumentException("角色名不许为空");
        }
        if(menuIds==null||menuIds.length==0){
            throw new IllegalArgumentException("必须为角色授权");
        }
        int rows = sysRoleDao.insertObject(entity);
        return sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
    }

    @Override
    public SysRoleMenuVo findObjectById(Integer id) {
        //1.合法性验证
        if(id==null||id<=0)
            throw new ServiceException("id的值不合法");
        //2.执行查询
        SysRoleMenuVo result=sysRoleDao.findObjectById(id);
        System.out.println(result);
        //3.验证结果并返回
        if(result==null)
            throw new ServiceException("此记录已经不存在");
        return result;
    }

    @Override
    public int updateObject(SysRole entity, Integer[] menuIds) {
        //1.合法性验证
        if(entity==null)
            throw new ServiceException("更新的对象不能为空");
        if(entity.getId()==null)
            throw new ServiceException("id的值不能为空");

        if(StringUtils.isEmpty(entity.getName()))
            throw new ServiceException("角色名不能为空");
        if(menuIds==null||menuIds.length==0)
            throw new ServiceException("必须为角色指定一个权限");

        //2.更新数据
        int rows=sysRoleDao.updateObject(entity);
        if(rows==0)
            throw new ServiceException("对象可能已经不存在");
        sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
        sysRoleMenuDao.insertObjects(entity.getId(),menuIds);
        //3.返回结果
        return rows;
    }

    @Override
    public List<CheckBox> findObjects() {
        return sysRoleDao.findObject();
    }
}
