package com.party.home.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.Play;
import com.party.domain.Recommend;

public interface HomeService 
{
	//添加推荐公告
	public void addRecommend(Recommend recommend);
	
	//删除推荐公告(id)
	public void deleteIdRecommend(Integer reid);
	
	//查询推荐公告（倒序）
	public List<Recommend> findByRecommend();
	
	//更新推荐公告
	public void updateIdRecommend(String title,String url,Integer reid);
	
	//增加轮播
	public void addByPlay(Play play);
	
	//查询轮播（倒序）
	public List<Play> findByPlay(String type);
	
	//更新轮播
	public void updateIdPlay(String url,Integer pid);
	
	//删除轮播(id)
	public void deleteIdPlay(Integer pid);
	
}
