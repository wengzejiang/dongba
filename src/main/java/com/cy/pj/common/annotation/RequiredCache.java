package com.cy.pj.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Package: com.cy.pj.common
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/31 0031 22:15
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequiredCache {
}
