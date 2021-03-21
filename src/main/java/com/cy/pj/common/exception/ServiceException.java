package com.cy.pj.common.exception;

/**
 * Package: com.cy.pj.common.exception
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/21 0021 13:42
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
public class ServiceException extends RuntimeException{
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
