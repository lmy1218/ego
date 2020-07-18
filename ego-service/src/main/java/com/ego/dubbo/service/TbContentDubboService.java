package com.ego.dubbo.service;

import com.ego.pojo.TbContent;

import java.util.List;

public interface TbContentDubboService {

    /**
     * 查询出最近的前n个
     * @param count
     * @param isSort
     * @return
     */
    List<TbContent> selByCount(int count, boolean isSort);
}
