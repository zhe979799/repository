package org.zhe.service.s_item_interface.item.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_spu_detail")
@Data
public class SpuDetail {
    @Id
    private Long spuId;
    private String description;
    private String specifications;
    @Column(name = "spec_template")
    private String specTemplate;
    @Column(name = "packing_list")
    private String packingList;
    @Column(name = "after_service")
    private String afterService;
}
