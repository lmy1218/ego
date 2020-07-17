package com.ego.dubbo.service.impl;
/**
 * @Project ego-parent
 * @Package com.ego.dubbo.service.impl
 * @author Administrator
 * @date 2020/7/17 7:45
 * @version V1.0
 */

import com.ego.common.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Administrator
 * @ClassName TbItemParamDubboServiceImpl
 * @Description 规格参数业务层
 * @date 2020/7/17 7:45
 **/

public class TbItemParamDubboServiceImpl implements TbItemParamDubboService {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    /**
     * 分页查询规格参数
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGrid showPage(int page, int rows) {
        // 设置分页
        PageHelper.startPage(page, rows);
        // 查询
        List<TbItemParam> params = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
        // 解析结果
        PageInfo<TbItemParam> pi = new PageInfo<>(params);
        // 组装返回结果
        EasyUIDataGrid grid = new EasyUIDataGrid();
        grid.setRows(pi.getList());
        grid.setTotal(pi.getTotal());

        return grid;
    }
}
