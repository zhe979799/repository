package org.zhe.service.s_item_interface.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "tb_spu")
@Data
public class Spu {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id ;
    @Column(name = "brand_id")
    private Long brandId;
    private Long cid1;
    private Long cid2;
    private Long cid3;
    private String title;
    @Column(name = "sub_title")
    private String subTitle;
    private Boolean saleable;
    @JsonIgnore
    private Boolean valid;
    @Column(name = "create_time")
    private Date createTime;
    @JsonIgnore
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    @Transient
    private String bname;
    @Transient
    private String cname;

}
