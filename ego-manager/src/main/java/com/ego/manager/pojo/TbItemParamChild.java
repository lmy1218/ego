package com.ego.manager.pojo;

import com.ego.pojo.TbItemParam;

/**
 * 用于封装返回给前端的数据
 */
public class TbItemParamChild extends TbItemParam {
	//	商品类目名称
	private String itemCatName;

	public String getItemCatName() {
		return itemCatName;
	}

	public void setItemCatName(String itemCatName) {
		this.itemCatName = itemCatName;
	}
	
	
}
