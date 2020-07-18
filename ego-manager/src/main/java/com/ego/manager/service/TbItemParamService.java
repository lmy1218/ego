package com.ego.manager.service;

import com.ego.common.pojo.EasyUIDataGrid;
import com.ego.common.pojo.EgoResult;
import com.ego.pojo.TbItemParam;

/**
 * @author Administrator
 * @version V1.0
 * @Project ego-parent
 * @Package com.ego.manager.service
 * @date 2020/7/17 7:56
 */
public interface TbItemParamService {

    EasyUIDataGrid showPage(int page, int rows);

    int deleteByIds(String ids) throws Exception;

    EgoResult existParam(Long catId);

    EgoResult save(TbItemParam param);
}
