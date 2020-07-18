package com.ego.dubbo.service;

import com.ego.pojo.TbItemDesc;

public interface TbItemDescDubboService {

    /**
     * 新增描述
     * @param itemDesc
     * @return
     */
    int insDesc(TbItemDesc itemDesc);


    /**
     * 根据主键查询
     * @param itemId
     * @return
     */
    TbItemDesc selByItemId(long itemId);
}
