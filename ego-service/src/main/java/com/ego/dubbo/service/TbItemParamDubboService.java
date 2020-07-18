package com.ego.dubbo.service;

import com.ego.common.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItemParam;

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

    int deleteByIds(String ids) throws Exception;

    TbItemParam selByCatId(Long catId);

    int insParam(TbItemParam param);
}
