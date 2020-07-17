package com.ego.dubbo.service;

import com.ego.common.pojo.EasyUIDataGrid;

/**
 * @author Administrator
 * @version V1.0
 * @Project ego-parent
 * @Package com.ego.dubbo.service
 * @date 2020/7/17 7:43
 */
public interface TbItemParamDubboService {

    // 分页查询规格参数
    EasyUIDataGrid showPage(int page, int rows);

}
