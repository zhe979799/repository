package org.zhe.service.s_item_interface.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_sku")
@Data
public class Sku {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    @Column(name = "spu_id")
    private Long spuId;
    private String title;
    private String images;
    private Long price;
    private String indexes;
    @Column(name = "own_spec")
    private String ownSpec;
    private Boolean enable;
    private Date createTime;
    private Date lastUpdateTime;

}
