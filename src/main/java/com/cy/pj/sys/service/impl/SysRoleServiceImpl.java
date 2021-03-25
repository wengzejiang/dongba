package com.cy.pj.sys.service.impl;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
