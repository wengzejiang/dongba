package com.cy.pj.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.cy.pj.sys.entity
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/27 0027 22:03
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 177030063138338860L;
    private Integer id;
    private String username;
    private String password;
    private String salt;//盐值
    private String email;
    private String mobile;
    private Integer valid=1;
    private Integer deptId;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
}
