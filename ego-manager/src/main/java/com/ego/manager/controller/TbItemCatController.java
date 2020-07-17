package com.ego.manager.controller;
/**
 * @Project ego-parent
 * @Package com.ego.manager.controller
 * @author Administrator
 * @date 2020/7/16 17:51
 * @version V1.0
 */

import com.ego.common.pojo.EasyUiTree;
import com.ego.manager.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Administrator
 * @ClassName TbItemCatController
 * @Description 商品类目控制器
 * @date 2020/7/16 17:51
 **/
@Controller
public class TbItemCatController {

    @Autowired
    private TbItemCatService tbItemCatServiceImpl;


    /**
     * 根据id查询所有子类目
     * @param id
     * @return
     */
    @RequestMapping("item/cat/list")
    @ResponseBody
    public List<EasyUiTree> showCat(@RequestParam(defaultValue = "0") long id) {
        return tbItemCatServiceImpl.showCatByPid(id);
    }



}
