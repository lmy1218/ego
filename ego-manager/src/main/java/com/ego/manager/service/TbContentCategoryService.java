package com.ego.manager.service;

import com.ego.common.pojo.EasyUiTree;
import com.ego.common.pojo.EgoResult;
import com.ego.pojo.TbContentCategory;

import java.util.List;

public interface TbContentCategoryService {

    /**
     * 查询类目
     * @param id
     * @return
     */
    List<EasyUiTree> showCategory(long id);


    /**
     * 新增
     * @param category
     * @return
     */
    EgoResult create(TbContentCategory category);


    /**
     * 类目重命名
     * @param category
     * @return
     */
    EgoResult update(TbContentCategory category);


    /**
     * 删除类目
     * @param category
     * @return
     */
    EgoResult delete(TbContentCategory category);

}
