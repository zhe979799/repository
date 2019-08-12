package org.zhe.service.s_item_service.item.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zhe.service.s_item_interface.item.pojo.Category;
import org.zhe.service.s_item_service.item.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("category")
public class CatrgoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoryListByPid(@RequestParam(value = "pid",defaultValue = "0") Long pid ){
        return ResponseEntity.ok(categoryService.queryCategoryListByPid(pid));
    }

    @GetMapping("list/{bid}")
    public ResponseEntity<List<Category>> queryCategoryByBid(@PathVariable("bid") Long bid ){
        return ResponseEntity.ok(categoryService.queryCategoryByBid(bid));
    }

}
