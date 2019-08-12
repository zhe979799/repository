package org.zhe.service.s_item_service.item.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.zhe.service.s_item_interface.item.pojo.Brand;
import org.zhe.service.s_item_service.item.mapper.factory.BrandAndCategoryMapperFactory;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> , ConditionMapper<Brand>, IdListMapper<Brand,Long> {

    @InsertProvider(type = BrandAndCategoryMapperFactory.class,method = "BrandAndCategory")
    void addBrandIdAndCategoryId (Long id,List<Long> categories);

    @SelectProvider(type = BrandAndCategoryMapperFactory.class,method = "queryBrandByCategoryId")
    List<Long> queryBrandByCategoryId(Long cid);

}
