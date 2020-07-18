package com.ego.manager.controller;

import com.ego.common.pojo.EasyUiTree;
import com.ego.common.pojo.EgoResult;
import com.ego.manager.service.TbContentCategoryService;
import com.ego.pojo.TbContentCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TbContentCategoryController {

    @Resource
    private TbContentCategoryService tbContentCategoryServiceImpl;


    @RequestMapping("content/category/list")
    @ResponseBody
    public List<EasyUiTree> showCategory(@RequestParam(defaultValue = "0") long id){
        return tbContentCategoryServiceImpl.showCategory(id);
    }


    /**
     * 类目创建
     * @param cate
     * @return
     */
    @RequestMapping("content/category/create")
    @ResponseBody
    public EgoResult create(TbContentCategory cate){
        return tbContentCategoryServiceImpl.create(cate);
    }

    /**
     * 类目修改
     * @param cate
     * @return
     */
    @RequestMapping("content/category/update")
    @ResponseBody
    public EgoResult update(TbContentCategory cate){
        return tbContentCategoryServiceImpl.update(cate);
    }


    /**
     * 类目删除
     * @param cate
     * @return
     */
    @RequestMapping("content/category/delete")
    @ResponseBody
    public EgoResult delete(TbContentCategory cate){
        return tbContentCategoryServiceImpl.delete(cate);
    }
}
