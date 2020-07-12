package com.ego.dubbo.service;

import com.ego.common.pojo.EasyUIDataGrid;

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

}
