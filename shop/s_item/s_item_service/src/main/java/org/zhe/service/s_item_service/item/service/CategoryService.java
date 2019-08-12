package org.zhe.service.s_item_service.item.service;


import org.zhe.service.s_item_interface.item.pojo.Category;

import java.util.List;

public interface CategoryService {

    List<Category> queryCategoryListByPid(Long pid);

    List<Category> queryCategoryByBid(Long bid);

    List<Category> queryListCategoryName(List<Long> ids );

    List<Category> queryCategoryListByBid(List<Long> bids);

}
