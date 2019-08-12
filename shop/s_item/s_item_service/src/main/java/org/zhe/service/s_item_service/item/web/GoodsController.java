package org.zhe.service.s_item_service.item.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zhe.common.s_common.item.vo.PageResult;
import org.zhe.common.s_common.item.vo.SkuRequest;
import org.zhe.common.s_common.item.vo.SpuDetailRequest;
import org.zhe.common.s_common.item.vo.SpuRequest;
import org.zhe.service.s_item_interface.item.pojo.Sku;
import org.zhe.service.s_item_interface.item.pojo.Spu;
import org.zhe.service.s_item_interface.item.pojo.SpuDetail;
import org.zhe.service.s_item_service.item.service.GoodsService;

import java.util.List;


@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<Spu>>  querySpuByPage(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "key",defaultValue = "",required = false) String key,
            @RequestParam(value = "saleable",required = false) Boolean saleable
    ){
        return ResponseEntity.ok(goodsService.querySpuByPage(page,rows,key,saleable));
    }

    @GetMapping("/spu/detail/{sid}")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable("sid") Long sid)throws Exception {
        return ResponseEntity.ok(goodsService.querySpuDetailBySpuId(sid));
    }

    @GetMapping("/sku/list")
    public ResponseEntity<List<Sku>> querySkuListBySpuId(@RequestParam("id") Long sid)throws Exception {
        return ResponseEntity.ok(goodsService.querySkuListBySpuId(sid));
    }


    @PostMapping("goods")
    public ResponseEntity<Void> addGoods(
            @RequestBody SpuRequest spuRequest
            ) throws Exception{
        goodsService.addGoods(spuRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("goods")
    public ResponseEntity<Void> editGoods(
            @RequestBody SpuRequest spuRequest
    ) throws Exception{
        goodsService.editGoods(spuRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
