package com.ego.manager.service.impl;
/**
 * @Project ego-parent
 * @Package com.ego.manager.service.impl
 * @author Administrator
 * @date 2020/7/17 7:57
 * @version V1.0
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.manager.pojo.TbItemParamChild;
import com.ego.manager.service.TbItemParamService;
import com.ego.pojo.TbItemParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @ClassName TbItemParamServiceImpl
 * @Description 商品规格参数业务
 * @date 2020/7/17 7:57
 **/
@Service
public class TbItemParamServiceImpl implements TbItemParamService {

    @Reference
    private TbItemParamDubboService tbItemParamDubboServiceImpl;

    @Reference
    private TbItemCatDubboService tbItemCatDubboServiceImpl;

    @Override
    public EasyUIDataGrid showPage(int page, int rows) {
        // 调用持久层服务，查询出所有规格参数
        EasyUIDataGrid grid = tbItemParamDubboServiceImpl.showPage(page, rows);
        List<TbItemParam> list = (List<TbItemParam>) grid.getRows();

        // 组装返回结果
        List<TbItemParamChild> children = list.stream().map(p -> {
            TbItemParamChild child = new TbItemParamChild();
            BeanUtils.copyProperties(p, child);
            child.setItemCatName(tbItemCatDubboServiceImpl.sleById(p.getItemCatId()).getName());
            return child;
        }).collect(Collectors.toList());
        grid.setRows(children);

        return grid;
    }
}
