package com.ego.order.service;

import com.ego.common.pojo.EgoResult;
import com.ego.common.pojo.TbItemChild;
import com.ego.order.pojo.MyOrderParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TbOrderService {

    /**
     * 确认订单信息
     * @param ids
     * @param request
     * @return
     */
    List<TbItemChild> showOrderCart(List<Long> ids, HttpServletRequest request);

    /**
     * 创建订单
     * @param param
     * @param request
     * @return
     */
    EgoResult create(MyOrderParam param, HttpServletRequest request);
}
