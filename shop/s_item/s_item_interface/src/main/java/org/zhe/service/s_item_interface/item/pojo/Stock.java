package org.zhe.service.s_item_interface.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_stock")
public class Stock {
    @Id
    @Column(name = "sku_id")
    private Long skuId;
    @Column(name = "seckill_stock")
    private Integer seckillStock;
    @Column(name = "seckill_total")
    private Integer seckillTotal;
    private Integer stock;
}
