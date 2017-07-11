package com.party.product.mapper;

import java.util.List;

import com.party.domain.ServeCategory;
/**
 * 
 * @author Caizhf
 * @date 2017年6月11日下午3:55:03
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 服务分类</p>
 *
 */
public interface ServeCategoryMapper {

	/**
	 * 查询所有的子分类
	 * @param type
	 * @return
	 */
	List<ServeCategory> listCategoryByServeType(String type);

}
