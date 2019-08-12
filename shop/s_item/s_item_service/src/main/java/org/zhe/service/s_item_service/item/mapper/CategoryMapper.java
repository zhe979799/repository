package org.zhe.service.s_item_service.item.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.zhe.service.s_item_interface.item.pojo.Category;
import org.zhe.service.s_item_service.item.mapper.factory.BrandAndCategoryMapperFactory;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryMapper extends Mapper<Category> , IdListMapper<Category,Long> {

    @SelectProvider(type = BrandAndCategoryMapperFactory.class,method = "queryCategoryByBrandId")
    List<Long> queryCategoryByBid(Long bid);

    @DeleteProvider(type = BrandAndCategoryMapperFactory.class,method = "deleteCategoryByBrandId")
    void deleteCategoryByBrandId(Long bid,Long cid);


}
