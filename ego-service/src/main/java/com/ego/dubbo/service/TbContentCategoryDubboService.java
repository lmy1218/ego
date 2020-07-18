package com.ego.dubbo.service;

import com.ego.pojo.TbContentCategory;

import java.util.List;

public interface TbContentCategoryDubboService {

    /**
     * 根据父ID查询所有子类目
     * @param id
     * @return
     */
    List<TbContentCategory> selByPid(long id);


    /**
     * 新增
     * @param category
     * @return
     */
    int inbsTbContentCategory(TbContentCategory category);

    /**
     * 修改内容
     * @param category
     * @return
     */
    int updisParent(TbContentCategory category);


    /**
     * 通过ID查询内容内容类目详细信息
     * @param id
     * @return
     */
    TbContentCategory selById(long id);
}
