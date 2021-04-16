package com.cy.pj.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Package: com.cy.pj.common.vo
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/4/14 0014 22:05
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Data
public class SysUserMenuVo implements Serializable {
    private static final long serialVersionUID = -8126757329276920059L;

    private Integer id;

    private String name;

    private String url;

    private List<SysUserMenuVo> childs;
}
