package com.ego.cart.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cart.interceptor.LoginInterceptor;
import com.ego.cart.service.CartService;
import com.ego.common.pojo.EgoResult;
import com.ego.common.pojo.TbItemChild;
import com.ego.common.utils.CookieUtils;
import com.ego.common.utils.HttpClientUtil;
import com.ego.common.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import com.ego.redis.dao.JedisDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private JedisDao jedisDaoImpl;

    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;

    @Value("${passport.url}")
    private String passportUrl;

    @Value("${cart.key}")
    private String cartKey;

    /**
     * 添加购物车
     * @param id
     * @param num
     * @param request
     */
    public void addCart(long id, int num, HttpServletRequest request) {
        List<TbItemChild> list = new ArrayList<TbItemChild>();
        EgoResult er = LoginInterceptor.threadLocal.get();
        String key = cartKey + ((LinkedHashMap)er.getData()).get("username");
        if(jedisDaoImpl.exists(key)){
            String json = jedisDaoImpl.get(key);
            if(json!=null && !json.equals("")){
                list = JsonUtils.jsonToList(json, TbItemChild.class);
                for(TbItemChild child : list){
                    if((long)child.getId() == id){
                        child.setNum(child.getNum() + num);
                        jedisDaoImpl.set(key, JsonUtils.objectToJson(list));

                        return ;
                    }
                }
            }
        }
        TbItem item = tbItemDubboServiceImpl.selById(id);
        TbItemChild child = new TbItemChild();

        child.setId(item.getId());
        child.setTitle(item.getTitle());
        child.setImages(item.getImage() == null || item.getImage().equals("") ? new String[1] : item.getImage().split(","));
        child.setNum(num);
        child.setPrice(item.getPrice());
        list.add(child);
        jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
    }



    public List<TbItemChild> showCart(HttpServletRequest request) {
        EgoResult er = LoginInterceptor.threadLocal.get();
        String key = cartKey + ((LinkedHashMap)er.getData()).get("username");

        String json = jedisDaoImpl.get(key);
        return JsonUtils.jsonToList(json, TbItemChild.class);
    }



    public EgoResult update(long id, int num, HttpServletRequest request) {
        EgoResult er = LoginInterceptor.threadLocal.get();
        String key = cartKey+((LinkedHashMap)er.getData()).get("username");

        String json = jedisDaoImpl.get(key);
        List<TbItemChild> list = JsonUtils.jsonToList(json, TbItemChild.class);
        for(TbItemChild child : list){
            if((long)child.getId() == id){
                child.setNum(num);
            }
        }
        String re = jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
        EgoResult egoResult = new EgoResult();
        if(re.equals("OK")){
            egoResult.setStatus(200);
        }
        return egoResult;
    }

    public EgoResult delete(long id, HttpServletRequest request) {

        EgoResult er = LoginInterceptor.threadLocal.get();
        String key = cartKey + ((LinkedHashMap)er.getData()).get("username");

        String json = jedisDaoImpl.get(key);
        List<TbItemChild> list = JsonUtils.jsonToList(json, TbItemChild.class);
        TbItemChild tbItemChild = null;
        for(TbItemChild child : list){
            if((long)child.getId() == id){
                tbItemChild = child;
            }
        }
        list.remove(tbItemChild);
        String re = jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
        EgoResult egoResult = new EgoResult();
        if(re.equals("OK")){
            egoResult.setStatus(200);
        }
        return egoResult;
    }
}
