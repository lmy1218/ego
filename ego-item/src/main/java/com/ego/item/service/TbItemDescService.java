package com.ego.item.service;

public interface TbItemDescService {

    /**
     * 根据商品ID查询商品描述
     * @param itemId
     * @return
     */
    String showDesc(long itemId);
}
