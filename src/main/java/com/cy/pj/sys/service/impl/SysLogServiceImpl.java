package com.cy.pj.sys.service.impl;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Package: com.cy.pj.sys.service.impl
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/20 0020 22:53
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
        if(pageCurrent==null || pageCurrent<1){
            throw new IllegalArgumentException("页码值无效");
        }
        //查询总记录数
        int rowCount = sysLogDao.getRowCount(username);
        if(rowCount==0){
            throw new ServiceException("记录不存在");
        }
        //查询总页数
        int pageSize=15;
        int startIndex=(pageCurrent-1)*pageSize;
        List<SysLog> records= sysLogDao.findPageObjects(username,startIndex,pageSize);
        //对数据进行封装
        return new PageObject<>(rowCount, records, pageCurrent, pageSize);
    }

    @Override
    public int deleteObjects(Integer... ids) {
        if(ids==null || ids.length==0){
            throw new IllegalArgumentException("请先选择");
        }
        int rows = sysLogDao.deleteObjects(ids);
        if(rows==0){
            throw new ServiceException("记录不存在");
        }
        return rows;
    }

    @Override
    @Async
    public void saveObject(SysLog entity) {
        sysLogDao.insertObject(entity);
    }
}
