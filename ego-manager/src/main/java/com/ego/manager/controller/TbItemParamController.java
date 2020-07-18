package com.ego.manager.controller;
/**
 * @Project ego-parent
 * @Package com.ego.manager.controller
 * @author Administrator
 * @date 2020/7/17 8:09
 * @version V1.0
 */

import com.ego.common.pojo.EasyUIDataGrid;
import com.ego.common.pojo.EgoResult;
import com.ego.manager.service.TbItemParamService;
import com.ego.pojo.TbItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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


    /**
     * 批量删除规格参数
     * @param ids
     * @return
     */
    @RequestMapping("item/param/delete")
    @ResponseBody
    public EgoResult delete(String ids) {
        EgoResult er = new EgoResult();
        try {
            int index = tbItemParamServiceImpl.deleteByIds(ids);
            if (index == 1) {
                er.setStatus(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            er.setMsg(e.getMessage());
        }
        return er;
    }


    /**
     * 判断参数模版是否存在
     * @param catId
     * @return
     */
    @RequestMapping("item/param/query/itemcatid/{catId}")
    @ResponseBody
    public EgoResult existParam(@PathVariable Long catId) {
        return tbItemParamServiceImpl.existParam(catId);
    }

    @RequestMapping("item/param/save/{catId}")
    @ResponseBody
    public EgoResult save(TbItemParam param, @PathVariable Long catId) {
        param.setItemCatId(catId);
        return tbItemParamServiceImpl.save(param);
    }

}
