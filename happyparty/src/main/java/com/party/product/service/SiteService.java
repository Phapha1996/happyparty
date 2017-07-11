package com.party.product.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.Site;

public interface SiteService 
{
	//分页获取所有场地信息
	public List<Site> findAllSite(int page,int size);
	
	//分页多条件模糊查询场地
	public List<Site> findLikeSite(String city,Integer startValue,Integer endValue,Integer startApply,Integer endApply,int page,int size);


	//按获取单个场地的所有信息
	public Site findBySite(int siid);
	
	//随机返回几条场地信息
	public List<Site> findRandomSite(int number);
	
	public List<Site> listLikeByTitle(String key, int pageNum, int pageSize);
}
