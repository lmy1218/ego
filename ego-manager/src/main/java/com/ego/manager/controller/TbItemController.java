package com.ego.manager.controller;
/**
 * @Project ego-parent
 * @Package com.ego.manager.controller
 * @author Administrator
 * @date 2020/7/12 22:40
 * @version V1.0
 */

import com.ego.common.pojo.EasyUIDataGrid;
import com.ego.manager.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @ClassName TbItemController
 * @Description 商品控制器
 * @date 2020/7/12 22:40
 **/
@Controller
public class TbItemController {


    @Autowired
    private TbItemService tbItemServiceImpl;

    /**
     * 分页查询商品
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("item/list")
    @ResponseBody
    public EasyUIDataGrid showItem(Integer page, Integer size) {
        return this.tbItemServiceImpl.showByPage(page, size);
    }

}
