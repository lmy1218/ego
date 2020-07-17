package com.ego.manager.service;

import com.ego.common.pojo.EasyUiTree;

import java.util.List;

/**
 * @author Administrator
 * @version V1.0
 * @Project ego-parent
 * @Package com.ego.manager.service
 * @date 2020/7/16 17:42
 */
public interface TbItemCatService {

    List<EasyUiTree> showCatByPid(long pid);
}
