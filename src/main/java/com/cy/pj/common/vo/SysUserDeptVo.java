package com.cy.pj.common.vo;

import com.cy.pj.sys.entity.SysDept;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.cy.pj.common.vo
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/27 0027 14:09
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Data
public class SysUserDeptVo implements Serializable {
    private static final long serialVersionUID = 5477389876913514595L;
    private Integer id;
    private String username;
    private String password;//md5
    private String salt;
    private String email;
    private String mobile;
    private Integer valid=1;
    private SysDept sysDept; //private Integer deptId;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
}
