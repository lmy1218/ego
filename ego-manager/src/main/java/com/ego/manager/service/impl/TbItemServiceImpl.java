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
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.manager.service.TbItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public EasyUIDataGrid showByPage(int page, int size) {
        log.info("【后台服务】商品分页查询开始");
        return tbItemDubboServiceImpl.showByPage(page, size);
    }
}
