package com.ego.item.controller;
/**
 * @Project ego-parent
 * @Package com.ego.item.controller
 * @author Administrator
 * @date 2020/7/18 16:57
 * @version V1.0
 */

import com.ego.item.service.TbItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @ClassName TbItemDescController
 * @Description 商品描述控制器
 * @date 2020/7/18 16:57
 **/
@Controller
public class TbItemDescController {

    @Autowired
    private TbItemDescService tbItemDescServiceImpl;


    @RequestMapping(value="item/desc/{id}.html",produces="text/html;charset=utf-8")
    @ResponseBody
    public String desc(@PathVariable long id){
        return tbItemDescServiceImpl.showDesc(id);
    }

}
