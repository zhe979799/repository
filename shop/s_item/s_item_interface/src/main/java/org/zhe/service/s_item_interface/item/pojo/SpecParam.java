package org.zhe.service.s_item_interface.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_spec_param")
public class SpecParam {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long cid;
    @Column(name = "group_id")
    private Long groupId;
    private String name;
    @Column(name = "numerics")
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;
}
