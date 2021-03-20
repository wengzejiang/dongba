package com.cy.pj.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.cy.pj.sys.entity
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/20 0020 22:09
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Data
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    //用户名
    private String username;
    //用户操作
    private String operation;
    //请求方法
    private String method;
    //请求参数
    private String params;
    //执行时长(毫秒)
    private Long time;
    //IP地址
    private String ip;
    //创建时间
    private Date createdTime;
}
