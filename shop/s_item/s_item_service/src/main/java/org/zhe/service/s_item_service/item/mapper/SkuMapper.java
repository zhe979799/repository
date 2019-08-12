package org.zhe.service.s_item_service.item.mapper;

import org.zhe.service.s_item_interface.item.pojo.Sku;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface SkuMapper extends Mapper<Sku>, IdListMapper<Sku,Long> {
}
