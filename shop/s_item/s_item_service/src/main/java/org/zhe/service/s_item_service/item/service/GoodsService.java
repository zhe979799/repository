package org.zhe.service.s_item_service.item.service;

import org.zhe.common.s_common.item.vo.PageResult;
import org.zhe.common.s_common.item.vo.SpuRequest;
import org.zhe.service.s_item_interface.item.pojo.Sku;
import org.zhe.service.s_item_interface.item.pojo.Spu;
import org.zhe.service.s_item_interface.item.pojo.SpuDetail;

import java.util.List;

public interface GoodsService {


    PageResult<Spu> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable);

    void addGoods(SpuRequest spuRequest);

    SpuDetail querySpuDetailBySpuId(Long sid);

    List<Sku> querySkuListBySpuId(Long sid);

    void editGoods(SpuRequest spuRequest);
}
