package org.zhe.service.s_item_service.item.service;

import org.aspectj.apache.bcel.generic.LineNumberGen;
import org.zhe.common.s_common.item.vo.PageResult;
import org.zhe.service.s_item_interface.item.pojo.Brand;
import org.zhe.service.s_item_interface.item.pojo.Category;

import java.util.List;

public interface BrandService {

    PageResult<Brand> queryBrandListByName(Integer page, Integer rowsPerPage, String name, String sortBy, Boolean desc);

    Brand backBrandById(Long id);

    void addBrand(Brand brand, List<Long> categories);

    void deleteBrandById(Long id);

    void editBrand(Brand brand, List<Long> categories);

    Brand queryBrandById(Long id);

    List<Brand> queryBrandListByCategoryId(Long cid);

}
