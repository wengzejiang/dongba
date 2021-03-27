package com.cy.pj.sys.service.impl;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.common.vo.SysUserDeptVo;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Package: com.cy.pj.sys.service.impl
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/27 0027 15:36
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Override
    public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
            //1.对参数进行校验
            if(pageCurrent==null||pageCurrent<1)
                throw new IllegalArgumentException("当前页码值无效");
            //2.查询总记录数并进行校验
            int rowCount=sysUserDao.getRowCount(username);
            if(rowCount==0)
                throw new ServiceException("没有找到对应记录");
            //3.查询当前页记录
            int pageSize=10;
            int startIndex=(pageCurrent-1)*pageSize;
            List<SysUserDeptVo> records=
                    sysUserDao.findPageObjects(username,
                            startIndex, pageSize);
            //4.对查询结果进行封装并返回
        return new PageObject<>(rowCount, records, pageCurrent, pageSize);
        }

    @Override
    public int validById(Integer id, Integer valid, String modifiedUser) {
        //1.合法性验证
        if(id==null||id<=0)
            throw new ServiceException("参数不合法,id="+id);
        if(valid!=1&&valid!=0)
            throw new ServiceException("参数不合法,valie="+valid);
        if(StringUtils.isEmpty(modifiedUser))
            throw new ServiceException("修改用户不能为空");
        //2.执行禁用或启用操作
        int rows=sysUserDao.validById(id, valid, modifiedUser);
        //3.判定结果,并返回
        if(rows==0)
            throw new ServiceException("此记录可能已经不存在");
        return rows;
    }
}
