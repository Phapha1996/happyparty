package com.party.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.Serve;

/**
 * 
 * @author Caizhf
 * @date 2017年6月11日下午2:49:35
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description:二级服务的数据访问接口 </p>
 *
 */
public interface ServeMapper {
	
	/**
	 * 查询所有的服务记录数量
	 * @return
	 * @type 三选一（达人服务、餐饮服务、包车服务）
	 */
	Integer countList(String type);

	/**
	 * 查询所有的服务，分页显示
	 * 需要映射图片
	 * @param type	三选一（达人服务、餐饮服务、包车服务）
	 * @param startIndex 起始记录
	 * @param pageSize 取的条数
	 * @return	所有的服务组成的list
	 */
	List<Serve> list(@Param("type") String type,@Param("startIndex") int startIndex,@Param("pageSize")int pageSize);

	/**
	 * 查询单个服务
	 * 需要映射图片列表、管理员
	 * @param sid
	 * @return
	 */
	Serve findById(int sid);

	/**
	 * 查询某个分类下的所有服务数量
	 * @param categoryId
	 * @return
	 */
	int countListByCategory(@Param("type") String type,@Param("categoryId") int categoryId);

	/**
	 * 查询某个分类下的所有服务，例如：查询达人服务下摄影达人的所有服务（服务商品列表）
	 * @param type	三选一（达人服务、餐饮服务、包车服务）
	 * @param categoryId	分类id
	 * @param startindex	
	 * @param pagesize
	 * @return
	 */
	List<Serve> listByCategoryId(@Param("type")String type,@Param("categoryId") int categoryId, 
			@Param("startIndex")int startIndex, @Param("pageSize")int pageSize);
	
	
	/**
	 * 模糊查询下有多少条数据
	 * @param type
	 * @param key
	 * @return
	 */
	int countListLikeByTitle(@Param("key")String key);
	
	/**
	 * 根据标题模糊查询
	 * @param key
	 * @param type
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<Serve> listLikeByTitle(@Param("key")String key, 
			@Param("startIndex")int startIndex, @Param("pageSize")int pageSize);
	
}
