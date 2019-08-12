package org.zhe.common.s_common.item.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private Long total;
    private Integer totalNum;
    private List<T> item;
}
