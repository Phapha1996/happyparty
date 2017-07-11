package com.party.orders.service;

import java.util.List;

import com.party.domain.Discuss;
/**
 * 
 * @author Caizhf
 * @date 2017年6月15日下午2:33:27
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 评价业务层接口</p>
 *
 */
public interface DiscussService {
	/**
	 * 功能：评论
	 * @param discuss 传值：传入productId、productType、content
	 * @return
	 */
	void save(Discuss discuss);

	/**
	 * 功能：查询指定商品下面的所有评价,分页显示
	 * @param productType
	 * @param productId
	 * @return
	 */
	List<Discuss> listByProduct(String productType, int productId, int pageNum, int pageSize);

}
