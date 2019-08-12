package org.zhe.service.s_item_service.item.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.zhe.common.s_common.item.enums.ExceptionEnum;
import org.zhe.common.s_common.item.exception.ShopException;
import org.zhe.common.s_common.item.vo.PageResult;
import org.zhe.common.s_common.item.vo.SkuRequest;
import org.zhe.common.s_common.item.vo.SpuDetailRequest;
import org.zhe.common.s_common.item.vo.SpuRequest;
import org.zhe.service.s_item_interface.item.pojo.*;
import org.zhe.service.s_item_service.item.mapper.*;
import org.zhe.service.s_item_service.item.service.BrandService;
import org.zhe.service.s_item_service.item.service.CategoryService;
import org.zhe.service.s_item_service.item.service.GoodsService;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    @Override
    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable) {
        PageHelper.startPage(page,rows);
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (saleable != null){
            criteria.andEqualTo("saleable", saleable ? 1 : 0);
        }
        if (key != null && !"".equals(key)){
            criteria.andLike("title","%"+key+"%");
        }
        List<Spu> spus = spuMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(spus)){
            throw  new ShopException(ExceptionEnum.GOODS_NOT_FOND);
        }
        loadCategoryAndBrandName(spus);
        PageInfo pageInfo = new PageInfo(spus);
        PageResult<Spu> pageResult = new PageResult<>();
        pageResult.setTotalNum(pageInfo.getPageNum());
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setItem(pageInfo.getList());
        return pageResult;
    }

    @Override
    @Transactional
    public void addGoods(SpuRequest spuRequest) {
        Spu spu = new Spu();
        Date date = new Date();
        spu.setBrandId(spuRequest.getBrandId());
        spu.setCid1(spuRequest.getCid1());
        spu.setCid2(spuRequest.getCid2());
        spu.setCid3(spuRequest.getCid3());
        spu.setTitle(spuRequest.getTitle());
        spu.setSubTitle(spuRequest.getSubTitle());
        spu.setSaleable(true);
        spu.setValid(true);
        spu.setCreateTime(date);
        spu.setLastUpdateTime(date);
        int insert = spuMapper.insert(spu);
        Long spuId = spu.getId();
        if(insert != 1 && spuId != null){
            throw  new ShopException(ExceptionEnum.GOODS_SPU_CODE_FAILED);
        }
        List<SkuRequest> skuRequests = spuRequest.getSkus();
        if(CollectionUtils.isEmpty(skuRequests)){
            throw new ShopException(ExceptionEnum.GOODS_SKU_DATE_FAILED);
        }
        SpuDetailRequest spuDetailRequest = spuRequest.getSpuDetail();
        if (spuDetailRequest == null) {
            throw new ShopException(ExceptionEnum.GOODS_SPUDETAIL_DATE_FAILED);
        }
        SpuDetail spuDetail = new SpuDetail();
        spuDetail.setSpuId(spuId);
        spuDetail.setAfterService(spuDetailRequest.getAfterService());
        spuDetail.setDescription(spuDetailRequest.getDescription());
        spuDetail.setPackingList(spuDetailRequest.getPackingList());
        spuDetail.setSpecifications(spuDetailRequest.getGenericSpec());
        spuDetail.setSpecTemplate(spuDetailRequest.getSpecialSpec());
        int insertSpuDetail = spuDetailMapper.insert(spuDetail);
        if(insertSpuDetail != 1){
            throw new ShopException(ExceptionEnum.GOODS_SPUDETAIL_CODE_FAILED);
        }
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        Stock stock = new Stock();
        for (SkuRequest skuR:skuRequests
             ) {
            sku.setEnable(skuR.getEnable());
            sku.setImages(skuR.getImages());
            sku.setIndexes(skuR.getIndexes());
            sku.setOwnSpec(skuR.getOwnSpec());
            sku.setPrice(skuR.getPrice());
            sku.setTitle(skuR.getTitle());
            sku.setCreateTime(date);
            sku.setLastUpdateTime(date);
            int insertSku = skuMapper.insert(sku);
            Long id = sku.getId();
            if (insertSku !=1 && id == null){
                throw new ShopException(ExceptionEnum.GOODS_SKU_CODE_FAILED);
            }
            stock.setSkuId(id);
            stock.setStock(skuR.getStock());
            int insertStock = stockMapper.insert(stock);
            if(insertStock != 1) {
                throw new ShopException(ExceptionEnum.GOODS_STOCK_DATA_FAILED);
            }
        }


    }

    @Override
    public SpuDetail querySpuDetailBySpuId(Long sid) {
        SpuDetail spuDetail = spuDetailMapper.selectByPrimaryKey(sid);
        if(spuDetail == null) {
            throw new ShopException(ExceptionEnum.GOODS_SPUDETAIL_NOT_FOND);
        }
        return spuDetail;
    }

    @Override
    public List<Sku> querySkuListBySpuId(Long sid) {
        Sku sku = new Sku();
        sku.setSpuId(sid);
        List<Sku> skus = skuMapper.select(sku);
        if (CollectionUtils.isEmpty(skus)) {
            throw new ShopException(ExceptionEnum.GOODS_SKU_NOT_FOND);
        }
        return skus;
    }

    @Override
    @Transactional
    public void editGoods(SpuRequest spuRequest) {
        Spu spu = new Spu();
        spu.setId(spuRequest.getId());
        spu.setBrandId(spuRequest.getBrandId());
        spu.setCid1(spuRequest.getCid1());
        spu.setCid2(spuRequest.getCid2());
        spu.setCid3(spuRequest.getCid3());
        spu.setTitle(spuRequest.getTitle());
        spu.setSubTitle(spuRequest.getSubTitle());
        spu.setSaleable(spuRequest.getSaleable());
        spuMapper.updateByPrimaryKey(spu);
        List<SkuRequest> skuRequests = spuRequest.getSkus();
        if (!CollectionUtils.isEmpty(skuRequests)){
            Sku sku = new Sku();
            Date date = new Date();
            Stock stock = new Stock();
            sku.setSpuId(spu.getId());
            for (SkuRequest skuR:skuRequests
            ) {
                sku.setId(skuR.getId());
                sku.setEnable(skuR.getEnable());
                sku.setImages(skuR.getImages());
                sku.setIndexes(skuR.getIndexes());
                sku.setOwnSpec(skuR.getOwnSpec());
                sku.setPrice(skuR.getPrice());
                sku.setTitle(skuR.getTitle());
                sku.setLastUpdateTime(date);
                skuMapper.updateByPrimaryKey(sku);
                stock.setSkuId(sku.getId());
                stock.setStock(skuR.getStock());
                stockMapper.updateByPrimaryKey(stock);
            }
        }
        SpuDetailRequest spuDetailRequest = spuRequest.getSpuDetail();
        SpuDetail spuDetail = new SpuDetail();
        spuDetail.setSpuId(spu.getId());
        spuDetail.setAfterService(spuDetailRequest.getAfterService());
        spuDetail.setDescription(spuDetailRequest.getDescription());
        spuDetail.setPackingList(spuDetailRequest.getPackingList());
        spuDetail.setSpecifications(spuDetailRequest.getSpecifications());
        spuDetail.setSpecTemplate(spuDetailRequest.getSpecTemplate());
        spuDetailMapper.updateByPrimaryKey(spuDetail);




    }

    private void loadCategoryAndBrandName(List<Spu> list){
        for (Spu spu:list
             ) {
            List<String> names = categoryService.queryListCategoryName(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream().map(Category::getName).collect(Collectors.toList());
            spu.setBname(brandService.queryBrandById(spu.getBrandId()).getName());
            spu.setCname(StringUtils.join(names,"/"));
        }
        if (CollectionUtils.isEmpty(list)){
            throw new ShopException(ExceptionEnum.GOODS_LOAD_NAME_FAILED);
        }
    }
}
