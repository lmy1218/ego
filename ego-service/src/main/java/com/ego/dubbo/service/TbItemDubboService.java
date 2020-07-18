package com.ego.dubbo.service;

import com.ego.common.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParam;

import java.util.List;

/**
 * @author Administrator
 * @version V1.0
 * @Project ego-parent
 * @Package com.ego.dubbo.service
 * @date 2020/7/12 20:54
 */
public interface TbItemDubboService {

    /**
     * 分页查询商品
     * @param page
     * @param size
     * @return
     */
    EasyUIDataGrid showByPage(int page, int size);

    /**
     * 新增商品
     * @param item
     * @param itemDesc
     * @param param
     * @return
     * @throws Exception
     */
    int insTbItemAndDesc(TbItem item, TbItemDesc itemDesc, TbItemParam param) throws Exception;


    TbItem selById(long id);

    List<TbItem> selAllByStatus(byte status);
}
