package org.zhe.service.s_item_service.item.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zhe.service.s_item_interface.item.pojo.SpecGroup;
import org.zhe.service.s_item_interface.item.pojo.SpecParam;
import org.zhe.service.s_item_service.item.service.SpecService;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecController {

    @Autowired
    private SpecService specService;

    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecGroupByCid(@PathVariable("cid") Long cid){
        return ResponseEntity.ok(specService.querySpecGroupByCid(cid));
    }

    @PutMapping("group")
    public ResponseEntity<Void> querySpecGroupByCid(@RequestBody SpecGroup specGroup){
        specService.editSpecGroup(specGroup);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("group")
    public ResponseEntity<Void> addSpecGroup(SpecGroup specGroup){
        specService.addSpecGroup(specGroup);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("group/{gid}")
    public ResponseEntity<Void> deleteSpecGroup(@PathVariable("gid")Long gid) {
        specService.deleteSpecGroupByGid(gid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> querySpecParamByGid(@RequestParam("gid") Long gid) {
        return ResponseEntity.ok(specService.querySpecParamByGid(gid));
    }

    @PutMapping("param")
    public ResponseEntity<Void> editSpecParam(@RequestBody SpecParam specParam) {
        specService.editSpecParam(specParam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("param")
    public ResponseEntity<Void> addSpecParam(@RequestBody  SpecParam specParam) {
        specService.addSpecParam(specParam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("param/{pid}")
    public ResponseEntity<Void> deleteSpecParam(@PathVariable("pid")Long pid) {
        specService.deleteSpecParamByPid(pid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("params/{cid}")
    public ResponseEntity<List<SpecParam>> querySpecParamByCid(@PathVariable("cid") Long cid) throws Exception{
        return ResponseEntity.ok(specService.querySpecParamByCid(cid));
    }

}
