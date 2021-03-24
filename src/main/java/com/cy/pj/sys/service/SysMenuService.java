package com.cy.pj.sys.service;

import java.util.List;
import java.util.Map;

/**
 * Package: com.cy.pj.sys.service
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/24 0024 22:19
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
public interface SysMenuService {

    List<Map<String, Object>> findObjects();

    int deleteObject(Integer id);
}
