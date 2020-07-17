package com.ego.manager.controller;
/**
 * @Project ego-parent
 * @Package com.ego.manager.controller
 * @author Administrator
 * @date 2020/7/17 8:09
 * @version V1.0
 */

import com.ego.common.pojo.EasyUIDataGrid;
import com.ego.manager.service.TbItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @ClassName TbItemParamController
 * @Description 规格参数控制器
 * @date 2020/7/17 8:09
 **/
@Controller
public class TbItemParamController {

    @Autowired
    private TbItemParamService tbItemParamServiceImpl;


    /**
     * 分页查询规格参数
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("item/param/list")
    @ResponseBody
    public EasyUIDataGrid showPage(int page, int rows) {
        return tbItemParamServiceImpl.showPage(page, rows);
    }

}
