package org.zhe.service.s_item_service.item.mapper.factory;

import tk.mybatis.mapper.mapperhelper.MapperTemplate;

import java.util.List;

public class BrandAndCategoryMapperFactory {

    public String queryCategoryByBrandId(Long bid) {
        return "select category_id from tb_category_brand where brand_id = "+bid.toString();
    }

    public String queryBrandByCategoryId(Long cid) {
        return "select brand_id from tb_category_brand where category_id = "+cid.toString();
    }



    public String deleteCategoryByBrandId(Long bid,Long cid) {
        StringBuffer stringBuffer = new StringBuffer("delete from tb_category_brand where brand_id = ");
        stringBuffer.append(bid);
        stringBuffer.append(" and  category_id = ");
        stringBuffer.append(cid);
        stringBuffer.append(" ");
        return stringBuffer.toString();
    }

    public String BrandAndCategory(Long id, List<Long> categories){
        String sql = "insert into tb_category_brand values";
        int i = 0;
        for (Long categoryId:categories) {

            sql = sql +"( "+categoryId.toString()+","+id.toString()+")" ;
            if(i+1 != categories.size()){
                sql = sql+",";
            }else{

            }
            i++;
        }
        return sql;
    }
}
