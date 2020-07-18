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


    /**
     * 批量删除规格参数
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(String ids) throws Exception {
        int index = 0;
        String[] idStr = ids.split(".");
        // 删除
        for (String id : idStr) {
            index += tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(id));
        }
        // 判断是否全部删除成功
        if (index == idStr.length) {
            return 1;
        } else {
            throw new Exception("删除规格参数失败");
        }
    }

    /**
     * 根据catId查询规格参数
     * @param catId
     * @return
     */
    @Override
    public TbItemParam selByCatId(Long catId) {
        // 组装查询条件
        TbItemParamExample example = new TbItemParamExample();
        example.createCriteria().andItemCatIdEqualTo(catId);
        // 查询
        List<TbItemParam> params = tbItemParamMapper.selectByExample(example);
        if (params != null && params.size() > 0) {
            return params.get(0);
        }
        return null;
    }


    /**
     * 规格参数新增
     * @param param
     * @return
     */
    @Override
    public int insParam(TbItemParam param) {
        return tbItemParamMapper.insertSelective(param);
    }
}
