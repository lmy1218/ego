package com.ego.dubbo.service.impl;
/**
 * @Project ego-parent
 * @Package com.ego.dubbo.service.impl
 * @author Administrator
 * @date 2020/7/12 20:55
 * @version V1.0
 */

import com.ego.common.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.mapper.TbItemMapper;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemExample;
import com.ego.pojo.TbItemParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Administrator
 * @ClassName TbItemDubboServiceImpl
 * @Description 商品业务类
 * @date 2020/7/12 20:55
 **/

public class TbItemDubboServiceImpl implements TbItemDubboService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public EasyUIDataGrid showByPage(int page, int size) {
        // 分页
        PageHelper.startPage(page, size);
        // 查询
        List<TbItem> items = tbItemMapper.selectByExample(new TbItemExample());
        // 解析结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(items);
        // 组装分页结果
        EasyUIDataGrid dataGrid = new EasyUIDataGrid();
        dataGrid.setRows(pageInfo.getList());
        dataGrid.setTotal(pageInfo.getTotal());

        return dataGrid;
    }


    /**
     * 商品新增
     * @param item
     * @param itemDesc
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public int insTbItemAndDesc(TbItem item, TbItemDesc itemDesc, TbItemParam param) throws Exception {
        int index = 0;
        index = tbItemMapper.insertSelective(item);
        index += tbItemDescMapper.insertSelective(itemDesc);
        index += tbItemParamMapper.insertSelective(param);
        if (index == 3) {
            return 1;
        } else {
            // 新增失败，数据回滚
           throw new  Exception("新增失败");
        }
    }

    @Override
    public TbItem selById(long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbItem> selAllByStatus(byte status) {
        TbItemExample example = new TbItemExample();
        example.createCriteria().andStatusEqualTo(status);
        return tbItemMapper.selectByExample(example);
    }
}
