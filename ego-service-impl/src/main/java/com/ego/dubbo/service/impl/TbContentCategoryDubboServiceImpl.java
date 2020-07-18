package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.mapper.TbContentCategoryMapper;
import com.ego.pojo.TbContentCategory;
import com.ego.pojo.TbContentCategoryExample;

import javax.annotation.Resource;
import java.util.List;

public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService {


    @Resource
    private TbContentCategoryMapper tbContentCategoryMapper;


    public List<TbContentCategory> selByPid(long id) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(id);
        return tbContentCategoryMapper.selectByExample(example);
    }

    public int inbsTbContentCategory(TbContentCategory category) {
        return tbContentCategoryMapper.insertSelective(category);
    }

    public int updisParent(TbContentCategory category) {
        return tbContentCategoryMapper.updateByPrimaryKeySelective(category);
    }

    public TbContentCategory selById(long id) {
        return tbContentCategoryMapper.selectByPrimaryKey(id);
    }
}
