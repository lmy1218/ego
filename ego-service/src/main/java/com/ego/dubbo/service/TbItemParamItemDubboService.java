package com.ego.dubbo.service;

import com.ego.pojo.TbItemParamItem;

public interface TbItemParamItemDubboService {

    /**
     * 根据商品ID茶隼商品规格参数
     * @param itemId
     * @return
     */
    TbItemParamItem selByItemId(long itemId);
}
