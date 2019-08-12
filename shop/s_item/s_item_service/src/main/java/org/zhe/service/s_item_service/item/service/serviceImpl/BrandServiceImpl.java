package org.zhe.service.s_item_service.item.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.zhe.common.s_common.item.enums.ExceptionEnum;
import org.zhe.common.s_common.item.exception.ShopException;
import org.zhe.common.s_common.item.vo.PageResult;
import org.zhe.service.s_item_interface.item.pojo.Brand;
import org.zhe.service.s_item_interface.item.pojo.Category;
import org.zhe.service.s_item_service.item.mapper.BrandMapper;
import org.zhe.service.s_item_service.item.mapper.CategoryMapper;
import org.zhe.service.s_item_service.item.service.BrandService;
import org.zhe.service.s_item_service.item.service.CategoryService;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.List;

@Service("brandService")
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public PageResult<Brand> queryBrandListByName(Integer page, Integer rowsPerPage, String query, String sortBy, Boolean desc)  {
        PageHelper.startPage(page,rowsPerPage);
        Example example = new Example(Brand.class);
        if (query != null && !"".equals(query)){
            example.createCriteria().orLike("name","%"+query+"%")
            .orEqualTo("letter",query.toUpperCase());
        }
        if(sortBy != null && !"".equals(sortBy)){
            if (desc != null ){
                if (desc){
                    example.setOrderByClause(sortBy +" DESC");
                }else{
                    example.setOrderByClause(sortBy +" ASC");
                }
            }else{
                throw new ShopException(ExceptionEnum.DONT_MODIFY_DATA);
            }
        }
        List<Brand> list = brandMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new ShopException(ExceptionEnum.BRAND_NOT_FOND);
        }
        PageInfo info = new PageInfo(list);
        PageResult<Brand> pageResult = new PageResult<>();
        pageResult.setItem(info.getList());
        pageResult.setTotal(info.getTotal());
        pageResult.setTotalNum(info.getPageNum());
        return pageResult;

    }

    @Override
    public Brand backBrandById(Long id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        if (brand == null){
            throw new ShopException(ExceptionEnum.BRAND_NOT_FOND);
        }
        return brand;
    }

    @Override
    @Transactional
    public void addBrand(Brand brand,List<Long> categories) {
        int insert = brandMapper.insert(brand);
        if(insert != 1 && !CollectionUtils.isEmpty(categories)){
            throw new ShopException(ExceptionEnum.BRAND_NOT_CODE);
        }else{
            brandMapper.addBrandIdAndCategoryId(brand.getId(),categories);
        }
    }

    @Override
    @Transactional
    public void deleteBrandById(Long id) {
        Brand brand = new Brand();
        brand.setId(id);
        Integer delete = brandMapper.delete(brand);
        if(delete == null || delete!= 1) {
            throw new ShopException(ExceptionEnum.BRAND_DELETE_FAILED);
        }
    }

    @Override
    @Transactional
    public void editBrand(Brand brand, List<Long> categories) {
        int insert = brandMapper.updateByPrimaryKey(brand);
        if(insert != 1 && !CollectionUtils.isEmpty(categories)){
            throw new ShopException(ExceptionEnum.BRAND_EDIT_FAILED);
        }else{
            List<Long> longs = categoryMapper.queryCategoryByBid(brand.getId());
            Long bid = brand.getId();
            for (Long cid:longs
                 ) {
                categoryMapper.deleteCategoryByBrandId(bid,cid);
            }
            brandMapper.addBrandIdAndCategoryId(brand.getId(),categories);
        }
    }

    @Override
    public Brand queryBrandById(Long id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        if (brand == null){
            throw new ShopException(ExceptionEnum.BRAND_NOT_FOND);
        }
        return brand;
    }

    public List<Brand> queryBrandListByCategoryId(Long cid){
        List<Long> longs = brandMapper.queryBrandByCategoryId(cid);
        if (CollectionUtils.isEmpty(longs)){
            throw new ShopException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        List<Brand> brands = brandMapper.selectByIdList(longs);
        if (CollectionUtils.isEmpty(longs)){
            throw new ShopException(ExceptionEnum.BRAND_NOT_FOND);
        }
        return brands;
    }


}
