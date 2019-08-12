package org.zhe.service.s_item_service.item.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zhe.common.s_common.item.enums.ExceptionEnum;
import org.zhe.common.s_common.item.exception.ShopException;
import org.zhe.service.s_item_interface.item.pojo.Category;
import org.zhe.service.s_item_service.item.mapper.CategoryMapper;
import org.zhe.service.s_item_service.item.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryCategoryListByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> list = categoryMapper.select(category);
        if(CollectionUtils.isEmpty(list)){
            throw new ShopException(ExceptionEnum.CATEGORY_NOT_FOND);
        }else{
            return list;
        }
    }

    @Override
    public List<Category> queryCategoryByBid(Long bid) {
        List<Long> longs = categoryMapper.queryCategoryByBid(bid);
        if(CollectionUtils.isEmpty(longs)){
            throw new ShopException(ExceptionEnum.BRAND_NOT_FOND);
        }
        List<Category> categories = new ArrayList<>();
        for (Long cid:longs ) {
            Category category = categoryMapper.selectByPrimaryKey(cid);
            categories.add(category);
        }
        if (CollectionUtils.isEmpty(categories)){
            throw new ShopException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        return categories;
    }

    public List<Category> queryListCategoryName(List<Long> ids ){
        List<Category> categories = categoryMapper.selectByIdList(ids);
        if (CollectionUtils.isEmpty(categories)){
            throw new ShopException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        return categories;
    }

    @Override
    public List<Category> queryCategoryListByBid(List<Long> bids) {
        List<Category> categories = categoryMapper.selectByIdList(bids);
        if (CollectionUtils.isEmpty(categories)){
            throw new ShopException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        return categories;
    }
}
