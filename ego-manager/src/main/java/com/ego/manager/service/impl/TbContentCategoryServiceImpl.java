package com.ego.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.EasyUiTree;
import com.ego.common.pojo.EgoResult;
import com.ego.common.utils.IDUtils;
import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.manager.service.TbContentCategoryService;
import com.ego.pojo.TbContentCategory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Reference
    private TbContentCategoryDubboService tbContentCategoryDubboServiceImpl;


    public List<EasyUiTree> showCategory(long id) {
        List<EasyUiTree> listTree = new ArrayList<EasyUiTree>();
        List<TbContentCategory> list = tbContentCategoryDubboServiceImpl.selByPid(id);

        for(TbContentCategory category : list){
            EasyUiTree tree = new EasyUiTree();
            tree.setId(category.getId());
            tree.setText(category.getName());
            tree.setState(category.getIsParent()? "closed" : "open");

            listTree.add(tree);
        }
        return listTree;
    }

    public EgoResult create(TbContentCategory category) {
        EgoResult er = new EgoResult();
        // 校验新增的目录名称是否已存在
        List<TbContentCategory> children = tbContentCategoryDubboServiceImpl.selByPid(category.getParentId());
        for(TbContentCategory child : children){
            if(child.getName().equals(category.getName())){
                er.setData("次类目已存在");
                return er;
            }
        }
        // 新增
        Date date = new Date();
        category.setCreated(date);
        category.setUpdated(date);
        category.setIsParent(false);
        category.setSortOrder(1);
        category.setStatus(1);
        long id = IDUtils.genItemId();
        category.setId(id);
        int index = tbContentCategoryDubboServiceImpl.inbsTbContentCategory(category);
        if(index > 0){
            TbContentCategory parent = new TbContentCategory();
            parent.setId(category.getParentId());
            parent.setIsParent(true);
            tbContentCategoryDubboServiceImpl.updisParent(parent);
        }
        er.setStatus(200);
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", id);
        er.setData(map);
        return er;
    }

    public EgoResult update(TbContentCategory category) {
        EgoResult er = new EgoResult();
        // 查询是否存在要修改的类目
        TbContentCategory categSelect = tbContentCategoryDubboServiceImpl.selById(category.getId());
        // 判断修改后的名称是否重复
        List<TbContentCategory> children = tbContentCategoryDubboServiceImpl.selByPid(categSelect.getParentId());
        for(TbContentCategory child : children){
            if(child.getName().equals(category.getName())){
                er.setData("该分类已存在");
                return er;
            }
        }
        // 修改
        int index = tbContentCategoryDubboServiceImpl.updisParent(category);
        if(index > 0){
            er.setStatus(200);
        }
        return er;
    }

    public EgoResult delete(TbContentCategory category) {
        EgoResult er = new EgoResult();
        category.setStatus(0);
        int index = tbContentCategoryDubboServiceImpl.updisParent(category);
        if(index > 0){
            TbContentCategory curr = tbContentCategoryDubboServiceImpl.selById(category.getId());
            List<TbContentCategory> list = tbContentCategoryDubboServiceImpl.selByPid(curr.getParentId());
            if(list==null || list.size() == 0){
                TbContentCategory parent = new TbContentCategory();
                parent.setId(curr.getParentId());
                parent.setIsParent(false);
                int result = tbContentCategoryDubboServiceImpl.updisParent(parent);
                if(result > 0){
                    er.setStatus(200);
                }
            }else{
                er.setStatus(200);
            }
        }
        return er;
    }
}
