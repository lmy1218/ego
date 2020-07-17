package com.ego.manager.service.impl;
/**
 * @Project ego-parent
 * @Package com.ego.manager.service.impl
 * @author Administrator
 * @date 2020/7/16 17:43
 * @version V1.0
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.EasyUiTree;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.manager.service.TbItemCatService;
import com.ego.pojo.TbItemCat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @ClassName TbItemCatServiceImpl
 * @Description 商品类目业务层
 * @date 2020/7/16 17:43
 **/
@Service
public class TbItemCatServiceImpl implements TbItemCatService {

    @Reference
    private TbItemCatDubboService tbItemCatDubboService;


    @Override
    public List<EasyUiTree> showCatByPid(long pid) {
        // 查询出所有类目
        List<TbItemCat> cats = tbItemCatDubboService.showCatByPid(pid);
        // 组装返回结果
        List<EasyUiTree> trees = new ArrayList<>();
        for (TbItemCat cat : cats) {
            EasyUiTree tree = new EasyUiTree();
            tree.setId(cat.getId());
            tree.setText(cat.getName());
            tree.setState(cat.getIsParent() ? "closed" : "open");
            trees.add(tree);
        }

        return trees;
    }
}
