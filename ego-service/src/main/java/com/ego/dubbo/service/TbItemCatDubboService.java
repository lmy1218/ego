package com.ego.dubbo.service;

import com.ego.pojo.TbItemCat;

import java.util.List;

/**
 * @Project ego-parent
 * @Package com.ego.dubbo.service
 * @author Administrator
 * @date 2020/7/16 11:37
 * @version V1.0
 */

public interface TbItemCatDubboService {
    // 根据pid查询类目
    List<TbItemCat> showCatByPid(long pid);
    // 根据类目ID查询
    TbItemCat sleById(long id);
}
