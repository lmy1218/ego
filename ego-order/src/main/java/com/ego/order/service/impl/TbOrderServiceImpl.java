package com.ego.order.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.EgoResult;
import com.ego.common.pojo.TbItemChild;
import com.ego.common.utils.CookieUtils;
import com.ego.common.utils.HttpClientUtil;
import com.ego.common.utils.IDUtils;
import com.ego.common.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.dubbo.service.TbOrderDubboService;
import com.ego.order.pojo.MyOrderParam;
import com.ego.order.service.TbOrderService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;
import com.ego.redis.dao.JedisDao;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class TbOrderServiceImpl implements TbOrderService {

    @Resource
    private JedisDao jedisDaoImpl;

    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;

    @Reference
    private TbOrderDubboService tbOrderDubboServiceImpl;

    @Value("${cart.key}")
    private String cartKey;

    @Value("${passport.url}")
    private String passportUrl;




    public List<TbItemChild> showOrderCart(List<Long> ids, HttpServletRequest request) {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        String json = HttpClientUtil.doPost(passportUrl + token);
        EgoResult er = JsonUtils.jsonToPojo(json, EgoResult.class);

        String key = cartKey + ((LinkedHashMap)er.getData()).get("username");
        String resultJson = jedisDaoImpl.get(key);
        List<TbItemChild> list = JsonUtils.jsonToList(resultJson, TbItemChild.class);
        List<TbItemChild> listNew = new ArrayList<TbItemChild>();
        for(TbItemChild child : list){
            for(Long id : ids){
                if((long)child.getId() == (long)id){
                    TbItem tbItem = tbItemDubboServiceImpl.selById(id);
                    if(tbItem.getNum() >= child.getNum()){
                        child.setEnough(true);
                    }else{
                        child.setEnough(false);
                    }
                    listNew.add(child);
                }
            }
        }
        return listNew;
    }

    public EgoResult create(MyOrderParam param, HttpServletRequest request) {
        TbOrder order = new TbOrder();
        order.setPayment(param.getPayment());
        order.setPaymentType(param.getPaymentType());
        long id = IDUtils.genItemId();
        order.setOrderId(id+"");
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);

        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        String json = HttpClientUtil.doPost(passportUrl + token);
        EgoResult er = JsonUtils.jsonToPojo(json, EgoResult.class);
        Map user = (LinkedHashMap) er.getData();

        order.setUserId(Long.parseLong(user.get("id").toString()));
        order.setBuyerNick(user.get("username").toString());
        order.setBuyerRate(0);
        for(TbOrderItem item : param.getOrderItems()){
            item.setId(IDUtils.genItemId() + "");
            item.setOrderId(id + "");
        }
        TbOrderShipping shipping = param.getOrderShipping();
        shipping.setOrderId(id + "");
        shipping.setCreated(date);
        shipping.setUpdated(date);

        EgoResult egoResult = new EgoResult();
        try{
            int index = tbOrderDubboServiceImpl.insOrder(order, param.getOrderItems(), shipping);
            if(index > 0){
                egoResult.setStatus(200);
                String json2 = jedisDaoImpl.get(cartKey + user.get("username"));
                List<TbItemChild> listCart = JsonUtils.jsonToList(json2, TbItemChild.class);
                List<TbItemChild> listNew = new ArrayList<TbItemChild>();
                for(TbItemChild child : listCart){
                    for(TbOrderItem item : param.getOrderItems()){
                        if(child.getId().longValue() == Long.parseLong(item.getItemId())){
                            listNew.add(child);
                        }
                    }
                }
                for(TbItemChild myNew : listNew){
                    listCart.remove(myNew);
                }
                jedisDaoImpl.set(cartKey + user.get("username"), JsonUtils.objectToJson(listCart));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return er;
    }
}
