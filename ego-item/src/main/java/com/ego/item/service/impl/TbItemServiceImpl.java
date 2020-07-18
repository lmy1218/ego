package com.ego.item.service.impl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.TbItemChild;
import com.ego.common.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.item.service.TbItemService;
import com.ego.pojo.TbItem;
import com.ego.redis.dao.JedisDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TbItemServiceImpl implements TbItemService {

    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;

    @Resource
    private JedisDao jedisDaoImpl;

    @Value("${redis.item.key}")
    private String itemKey;


    public TbItemChild show(long id) {
        String key = itemKey + id;
        if(jedisDaoImpl.exists(key)){
            String json = jedisDaoImpl.get(key);

            if(json != null && !json.equals("")){
                return JsonUtils.jsonToPojo(json, TbItemChild.class);
            }
        }
        TbItem item = tbItemDubboServiceImpl.selById(id);
        TbItemChild child = new TbItemChild();
        child.setId(item.getId());
        child.setTitle(item.getTitle());
        child.setPrice(item.getPrice());
        child.setSellPoint(item.getSellPoint());
        child.setImages(item.getImage()!=null&&!item.equals("")?item.getImage().split(","):new String[1]);

        jedisDaoImpl.set(key, JsonUtils.objectToJson(child));
        return child;
    }
}
