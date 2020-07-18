package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbContentDubboService;
import com.ego.mapper.TbContentMapper;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

public class TbContentDubboServiceImpl implements TbContentDubboService {

    @Resource
    private TbContentMapper tbContentMapper;


    public List<TbContent> selByCount(int count, boolean isSort) {
        TbContentExample example = new TbContentExample();
        if(isSort){
            example.setOrderByClause("updated desc");
        }

        if(count != 0){
            PageHelper.startPage(1, count);
            List<TbContent> list = tbContentMapper.selectByExample(example);
            PageInfo<TbContent> pi = new PageInfo<TbContent>(list);
            return pi.getList();
        }else{
            return tbContentMapper.selectByExampleWithBLOBs(example);
        }
    }
}
