package com.cy.pj.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.cy.pj.sys.entity
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/25 0025 22:47
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = -356538509994150709L;
    private Integer id;
    private String name;
    private String note;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
}
