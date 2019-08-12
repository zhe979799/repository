package org.zhe.common.s_common.item.vo;

import lombok.Data;

import java.util.List;

@Data
public class SpuDetailRequest {
    private String specifications;
    private String specTemplate;
    private String afterService;
    private String description;
    private String genericSpec;
    private String packingList;
    private String specialSpec;

}
