package org.zhe.service.s_item_service.item.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zhe.common.s_common.item.exception.ShopException;
import org.zhe.common.s_common.item.vo.PageResult;
import org.zhe.service.s_item_interface.item.pojo.Brand;
import org.zhe.service.s_item_interface.item.pojo.Category;
import org.zhe.service.s_item_service.item.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("list")
    public ResponseEntity<PageResult<Brand>> queryBrandListByName(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rowsPerPage,
            @RequestParam(value = "query",defaultValue = "",required = false) String query,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "desc",required = false)Boolean desc
    ) throws ShopException {
        PageResult<Brand> pageResult = brandService.queryBrandListByName(page, rowsPerPage, query, sortBy, desc);
        return ResponseEntity.ok(pageResult);
    }

    @GetMapping("back")
    public ResponseEntity<Brand> backBrandById(@RequestParam(value = "id",required = true)Long id) throws Exception{
        return ResponseEntity.ok(brandService.backBrandById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addBrand(Brand brand,@RequestParam("cids") List<Long> categories) throws Exception{
        brandService.addBrand(brand,categories);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBrandById(@RequestParam(value = "id")Long id) throws Exception{
        brandService.deleteBrandById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> editBrand(Brand brand,@RequestParam("cids") List<Long> categories) throws Exception{
        brandService.editBrand(brand,categories);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("cid/{id}")
    public ResponseEntity<List<Brand>> queryCategoryListByBrandId(@PathVariable("id") Long cid) throws Exception{
        return ResponseEntity.ok(brandService.queryBrandListByCategoryId(cid));
    }

}
