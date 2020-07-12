package com.ego.manager.service;

import com.ego.common.pojo.EasyUIDataGrid;

/**
 * @author Administrator
 * @version V1.0
 * @Project ego-parent
 * @Package com.ego.manager.service
 * @date 2020/7/12 22:26
 */
public interface TbItemService {

    EasyUIDataGrid showByPage(int page, int size);

}
