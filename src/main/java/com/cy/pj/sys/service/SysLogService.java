package com.cy.pj.sys.service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Package: com.cy.pj.sys.service
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/20 0020 22:53
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Service
public interface SysLogService {


    //基于用户请求，进行日志信息的分页查询，并对结果进行封装和计算
    PageObject<SysLog> findPageObjects(String username, Integer pageCurrent);

    int deleteObjects(Integer... ids);

    void saveObject(SysLog entity);
}
