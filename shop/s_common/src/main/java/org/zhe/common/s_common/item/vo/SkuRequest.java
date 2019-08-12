package org.zhe.common.s_common.item.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SkuRequest {
    private Long id;
    private String title;
    private String images;
    private Long price;
    private String indexes;
    private String ownSpec;
    private Boolean enable;
    private Integer stock;
}
