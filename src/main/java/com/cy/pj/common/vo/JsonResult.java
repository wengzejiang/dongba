package com.cy.pj.common.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Package: com.cy.pj.common.vo
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/21 0021 13:58
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class JsonResult implements Serializable {
    private static final long serialVersionUID = -56456712L;
    private int state=1;//1表示OK，0表示Error
    private String message="OK";
    private Object data;

    public JsonResult(int state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public JsonResult(int state) {
        this.state = state;
    }

    public JsonResult(String message) {
        this.message = message;
    }

    public JsonResult(Object data) {
        this.data = data;
    }
    /**出现异常时时调用*/
    public JsonResult(Throwable t){
        this.state=0;
        this.message=t.getMessage();
    }
}


