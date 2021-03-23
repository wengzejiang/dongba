package com.cy;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private SysLogDao sysLogDao;

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private SysMenuDao sysMenuDao;

    @Test
    void contextLoads() {
        int rowCount = sysLogDao.getRowCount("a");
        System.out.println(rowCount);
    }

    @Test
    public void testFindPageObjects(){
        List<SysLog> pageObjects = sysLogDao.findPageObjects("admin", 0, 3);
        for(SysLog log:pageObjects){
            System.out.println(log);
        }
    }

    @Test
    public void testFind(){
        PageObject<SysLog> admin = sysLogService.findPageObjects("admin", 2);
        System.out.println(admin);
    }
    @Test
    public void testFind1(){
        List<Map<String, Object>> objects =sysMenuDao.findObjects();
        for(Map<String, Object> map:objects){
            System.out.println(map);
        }
    }


}
