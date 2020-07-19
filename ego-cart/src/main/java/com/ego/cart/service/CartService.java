package com.ego.cart.service;

import com.ego.common.pojo.EgoResult;
import com.ego.common.pojo.TbItemChild;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CartService {

    /**
     * 加入购物车
     * @param id
     * @param num
     * @param request
     */
    void addCart(long id, int num, HttpServletRequest request);


    /**
     * 显示购物车商品
     * @param request
     * @return
     */
    List<TbItemChild> showCart(HttpServletRequest request);

    /**
     * 根据ID修改数量
     * @param id
     * @param num
     * @param request
     * @return
     */
    EgoResult update(long id, int num, HttpServletRequest request);

    /**
     * 删除购物车商品
     * @param id
     * @param request
     * @return
     */
    EgoResult delete(long id, HttpServletRequest request);
}
