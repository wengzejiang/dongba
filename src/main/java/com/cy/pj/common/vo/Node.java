package com.cy.pj.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Package: com.cy.pj.common.vo
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/25 0025 21:12
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Data
public class Node implements Serializable {

    private Integer id;
    private String name;
    private Integer parentId;
}
