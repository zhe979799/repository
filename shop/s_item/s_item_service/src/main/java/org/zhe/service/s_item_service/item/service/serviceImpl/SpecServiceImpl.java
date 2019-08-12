package org.zhe.service.s_item_service.item.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.zhe.common.s_common.item.enums.ExceptionEnum;
import org.zhe.common.s_common.item.exception.ShopException;
import org.zhe.service.s_item_interface.item.pojo.SpecGroup;
import org.zhe.service.s_item_interface.item.pojo.SpecParam;
import org.zhe.service.s_item_service.item.mapper.SpecGroupMapper;
import org.zhe.service.s_item_service.item.mapper.SpecParamMapper;
import org.zhe.service.s_item_service.item.service.SpecService;

import java.util.List;

@Service("specService")
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    public List<SpecGroup> querySpecGroupByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> specGroups = specGroupMapper.select(specGroup);
        if(CollectionUtils.isEmpty(specGroups)){
            throw new ShopException(ExceptionEnum.SPEC_GROUP_NOT_FOND);
        }
        return specGroups;
    }

    @Override
    public List<SpecParam> querySpecParamByGid(Long gid) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        List<SpecParam> specParams = specParamMapper.select(specParam);
        if (CollectionUtils.isEmpty(specParams)){
            throw new ShopException(ExceptionEnum.SPEC_PARAM_NOT_FOND);
        }
        return specParams;
    }

    @Override
    @Transactional
    public void editSpecParam(SpecParam specParam) {
        specParamMapper.updateByPrimaryKey(specParam);
    }

    @Override
    @Transactional
    public void deleteSpecParamByPid(Long pid) {
        int i = specParamMapper.deleteByPrimaryKey(pid);
        if(i != 1){
            throw new ShopException(ExceptionEnum.SPEC_PARAM_DELETE_FAILED);
        }
    }

    @Override
    @Transactional
    public void editSpecGroup(SpecGroup specGroup) {
        specGroupMapper.updateByPrimaryKey(specGroup);
    }

    @Override
    @Transactional
    public void deleteSpecGroupByGid(Long gid) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        List<SpecParam> specParams = specParamMapper.select(specParam);
        for (SpecParam param:specParams
             ) {
            specParamMapper.delete(param);
        }
        specGroupMapper.deleteByPrimaryKey(gid);
    }

    @Override
    @Transactional
    public void addSpecGroup(SpecGroup specGroup) {
        int insert = specGroupMapper.insert(specGroup);
        if(insert != 1) {
            throw new ShopException(ExceptionEnum.SPEC_GROUP_CODE_FAILED);
        }
    }

    @Override
    @Transactional
    public void addSpecParam(SpecParam specParam) {
        int insert = specParamMapper.insert(specParam);
        if(insert != 1) {
            throw new ShopException(ExceptionEnum.SPEC_PARAM_CODE_FAILED);
        }
    }

    @Override
    public List<SpecParam> querySpecParamByCid(Long cid) {
        SpecParam param = new SpecParam();
        param.setCid(cid);
        List<SpecParam> params = specParamMapper.select(param);
        if(CollectionUtils.isEmpty(params)){
            throw new ShopException(ExceptionEnum.SPEC_PARAM_NOT_FOND);
        }
        return params;
    }
}
