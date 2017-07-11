package com.party.product.service;

import java.util.List;

import com.party.domain.Collect;
import com.party.domain.Serve;

public interface CollectService 
{
	//增加收藏信息
	public boolean addCollect(Collect collect,int uid);
	
	//按id删除收藏信息
	public void deleteByIdCollect(int coid);
	
	//查询用户收藏里的场地信息
	public List<Collect> findCollectBySite(int uid,int page,int size);
	
	//查询二级服务类
	public List<Collect> findTypeServe(Integer uid,String type,Integer page,Integer size);


	//按收藏id查询二级服务
	public Collect findIdServe(Integer coid);
	
	//按收藏id查询场地信息
	public Collect findIdSite(Integer coid);
	
	//查询套餐
	public List<Collect> findTypeMeal(Integer uid,String type,Integer page,Integer size);


	//按收藏id查询二级服务
	public Collect findIdMeal(Integer coid);
}
