package org.zhe.service.s_item_service.item.service;

import org.zhe.service.s_item_interface.item.pojo.SpecGroup;
import org.zhe.service.s_item_interface.item.pojo.SpecParam;

import java.util.List;

public interface SpecService {

    List<SpecGroup> querySpecGroupByCid(Long cid);

    List<SpecParam> querySpecParamByGid(Long gid);

    void editSpecParam(SpecParam specParam);

    void deleteSpecParamByPid(Long pid);

    void editSpecGroup(SpecGroup specGroup);

    void deleteSpecGroupByGid(Long gid);

    void addSpecGroup(SpecGroup specGroup);

    void addSpecParam(SpecParam specParam);

    List<SpecParam> querySpecParamByCid(Long cid);
}
