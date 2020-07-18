package com.ego.dubbo.service.impl;
/**
 * @Project ego-parent
 * @Package com.ego.dubbo.service.impl
 * @author Administrator
 * @date 2020/7/16 11:39
 * @version V1.0
 */

import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.mapper.TbItemCatMapper;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemCatExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @ClassName TbItemCatDubboServiceImpl
 * @Description TODO
 * @date 2020/7/16 11:39
 **/
@Slf4j
@Service
public class TbItemCatDubboServiceImpl implements TbItemCatDubboService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> showCatByPid(long pid) {
        log.info("根据父id查询所有子类目");
        // 组装查询条件
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(pid);
        return tbItemCatMapper.selectByExample(example);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public TbItemCat sleById(long id) {
        return tbItemCatMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbItemCat> show(long pid) {
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(pid);
        return tbItemCatMapper.selectByExample(example);
    }

    @Override
    public TbItemCat selById(Long cid) {
        return tbItemCatMapper.selectByPrimaryKey(cid);
    }
}
