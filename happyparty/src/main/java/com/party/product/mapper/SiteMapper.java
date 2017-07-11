package com.party.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.party.domain.Site;

@Mapper
public interface SiteMapper 
{
	//分页查询所有场地信息
	public List<Site> findAllSite(@Param("start")int start,@Param("size")int size);

	//多条件模糊查询场地
	public List<Site> selectLikeSite(@Param("city")String city,@Param("startValue")Integer startValue,@Param("endValue")Integer endValue,@Param("startApply")Integer startApply,@Param("endApply")Integer endApply,@Param("start")int start,@Param("size")int size);

	//按获取单个场地的所有信息
	public Site findByIdSite(int siid);
	
	//随机查询几条场地信息
	public List<Site> findRandSite(int number);
	
	int countLikeByTitle(String key);
	
	/**
	 * 按标题模糊查询
	 * @param key
	 * @param startindex
	 * @param pageSize
	 * @return
	 */
	List<Site> listLikeByTitle(@Param("key")String key, 
			@Param("startIndex")int startIndex, @Param("pageSize")int pageSize);


}
