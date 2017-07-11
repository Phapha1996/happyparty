package com.party.product.service;

import java.util.List;

import com.party.domain.Serve;
import com.party.domain.ServeCategory;
/**
 * 
 * @author Caizhf
 * @date 2017年6月11日下午2:42:31
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 二级服务的业务接口</p>
 *
 */
public interface ServeService {

	/**
	 * 分页得到所有的服务
	 * @param pageNum	页码
	 * @param type	服务类型（达人服务、餐饮服务、包车服务）
	 * @return
	 */
	List<Serve> listServe(int pageNum, String type);

	/**
	 * 分页得到某个分类下面的所有服务
	 * @param pageNum	页码
	 * @param type	类型（达人服务、餐饮服务、包车服务）
	 * @param categoryId	分类id号
	 * @return
	 */
	List<Serve> listServeByCategory(int pageNum, String type, int categoryId);

	/**
	 * 得到整个服务的对象
	 * @param sid
	 * @return
	 */
	Serve getServe(int sid);

	/**
	 * 根据服务类型得到该类型所有的分类
	 * @param type	服务类型（达人服务、餐饮服务、包车服务）
	 * @return
	 */
	List<ServeCategory> listCategory(String type);

	List<Serve> listLikeByTitle(int pageNum,  int pageSize, String key);
	
	

}
