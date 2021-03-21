package com.cy.pj.common.vo;

import com.cy.pj.sys.entity.SysLog;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Package: com.cy.pj.sys.vo
 * Description： TODO
 * Author: wengzejiang
 * Date: Created in 2021/3/21 0021 11:05
 * Company: 暂无
 * Version: 0.0.1
 * Modified By:
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class PageObject<T> implements Serializable {
    private static final long serialVersionUID = 213123L;
    //总记录数
    private Integer rowCounter;
    //当前页要呈现的记录
    private List<T> records;
    //总页数
    private Integer pageCounter;
    //当前页码值
    private Integer pageCurrent;
    //页面大小
    private Integer pageSize;

    public PageObject(Integer rowCounter, List<T> records, Integer pageCurrent, Integer pageSize) {
        this.rowCounter = rowCounter;
        this.records = records;
        this.pageCurrent = pageCurrent;
        this.pageSize = pageSize;
        //计算总页数
        this.pageCounter=(rowCounter-1)/pageSize+1;
    }
}
