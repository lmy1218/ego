package com.ego.manager.service.impl;
/**
 * @Project ego-parent
 * @Package com.ego.manager.service.impl
 * @author Administrator
 * @date 2020/7/12 22:28
 * @version V1.0
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.EasyUIDataGrid;
import com.ego.common.utils.IDUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.manager.service.TbItemService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Administrator
 * @ClassName TbItemServiceImpl
 * @Description 商品业务层
 * @date 2020/7/12 22:28
 **/
@Slf4j
@Service
public class TbItemServiceImpl implements TbItemService {

    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;

    /**
     * 分页查询商品
     * @param page
     * @param size
     * @return
     */
    @Override
    public EasyUIDataGrid showByPage(int page, int size) {
        log.info("【后台服务】商品分页查询开始");
        return tbItemDubboServiceImpl.showByPage(page, size);
    }

    @Override
    public int save(TbItem item, String itemParams, String desc) throws Exception {
        // 组装参数
        long id = IDUtils.genItemId();
        item.setId(id);
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        item.setStatus(((byte) 1));

        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(id);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);

        TbItemParam param = new TbItemParam();
        param.setUpdated(date);
        param.setCreated(date);
        param.setParamData(itemParams);
        param.setId(id);
        int index = 0;
        index = tbItemDubboServiceImpl.insTbItemAndDesc(item, itemDesc, param);

        return index;
    }


}
