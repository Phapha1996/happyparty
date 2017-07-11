package com.party.home.service.impl;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.party.domain.Play;
import com.party.domain.Recommend;
import com.party.exception.ServiceException;
import com.party.home.mapper.HomeMapper;
import com.party.home.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService
{
	private final Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);
	
	@Autowired
	private HomeMapper homeMapper;
	
	//添加推荐公告
	public void addRecommend(Recommend recommend)
	{
		int status=homeMapper.insterRecommend(recommend);
		if(status==0)
			throw new ServiceException("增加轮播失败！");
	}
	
	
	//删除推荐公告(id)
	public void deleteIdRecommend(Integer reid)
	{
		int status=homeMapper.DeleteByIdRecommend(reid);
		if(status==0)
			throw new ServiceException("删除轮播失败！");
	}
	
	//查询推荐公告（倒序）
	public List<Recommend> findByRecommend()
	{
		List<Recommend> recommend=homeMapper.SelectByRecommend();
		return recommend;
	}
	
	//更新推荐公告
	public void updateIdRecommend(String title,String url,Integer reid)
	{
		int status=homeMapper.updateByIdRecommend(title, url, reid);
		if(status==0)
			throw new ServiceException("更新轮播失败！");
	}
	
	
	//增加轮播
	public void addByPlay(Play play)
	{
		int status=homeMapper.insterPlay(play);
		if(status==0)
			throw new ServiceException("添加轮播失败！");
	}
	
	//查询轮播（倒序）
	public List<Play> findByPlay(String type)
	{
		List<Play> play=homeMapper.SelectByPlay(type);
		return play;
	}
	
	//更新轮播链接
	public void updateIdPlay(String url,Integer pid)
	{
		int status=homeMapper.updateByIdPlay(url, pid);
		if(status==0)
			throw new ServiceException("更新轮播信息失败！");
	}
	
	//删除轮播(id)
	@Transactional
	public void deleteIdPlay(Integer pid)
	{
		Play play=homeMapper.SelectByIdPlay(pid);
		File file = new File(play.getImg_url());
		//删除图片，并且删除记录
		if(!file.delete()||homeMapper.DeleteByIdPlay(pid)==0)
			throw new ServiceException("删图失败");	
	}
}
