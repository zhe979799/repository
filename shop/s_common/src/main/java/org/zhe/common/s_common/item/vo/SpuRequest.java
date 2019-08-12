package org.zhe.common.s_common.item.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Data
public class SpuRequest {
    private Long id;
    private Long brandId;
    private Long cid1;
    private Long cid2;
    private Long cid3;
    private String title;
    private String subTitle;
    private Boolean saleable;
    private List<SkuRequest> skus;
    private SpuDetailRequest spuDetail;
}
