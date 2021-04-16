package com.cy.pj.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Package: com.cy.pj.common.vo
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/27 0027 10:51
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Data
public class SysRoleMenuVo implements Serializable {

    /**角色id*/
    private Integer id;
    /**角色名称*/
    private String name;
    /**角色备注*/
    private String note;
    /**角色对应的菜单id*/
    private List<Integer> menuIds;
}
