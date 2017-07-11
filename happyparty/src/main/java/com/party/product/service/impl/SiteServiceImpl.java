package com.party.product.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.party.domain.Site;
import com.party.dto.Page;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
import com.party.product.mapper.SiteMapper;
import com.party.product.service.SiteService;

import com.party.user.service.impl.UserServiceImpl;


@Service
public class SiteServiceImpl implements SiteService
{
	private final Logger logger = LoggerFactory.getLogger(SiteServiceImpl.class);
	
	@Autowired
	private SiteMapper siteMapper;
	
	//分页获取所有场地信息
	public List<Site> findAllSite(int page,int size)
	{
		if(page==0||size==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int start=size*(page-1);
		List<Site> site=siteMapper.findAllSite(start, size);
		if(site.equals(null))
			throw new ServiceException("查询所有场地失败");
		return site;
	}
	
	
	//分页多条件模糊查询场地
	public List<Site> findLikeSite(String city,Integer startValue,Integer endValue,Integer startApply,Integer endApply,int page,int size)
	{
		int start=size*(page-1);
		List<Site> site=siteMapper.selectLikeSite(city, startValue, endValue, startApply, endApply, start, size);
		if(site.equals(null)||site.size()==0)
			throw new ServiceException("查询所有场地失败");
		return site;
			
	}
	
	
	//按获取单个场地的所有信息
	public Site findBySite(int siid)
	{
		if(siid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		Site site = siteMapper.findByIdSite(siid);
		if(site.equals(null))
			throw new ServiceException("查询场地失败");
		return site;
	}
	
	
	//随机返回几条场地信息
	public List<Site> findRandomSite(int number)
	{
		if(number==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		List<Site> site=siteMapper.findRandSite(number);
		if(site.equals(null))
			throw new ServiceException("随机查询场地失败");
		return site;
	}


	@Override
	public List<Site> listLikeByTitle(String key, int pageNum, int pageSize) {
		int totleRecord = siteMapper.countLikeByTitle(key);
		Page page = null;
		if (pageNum == 0)
			page = new Page(totleRecord, 1);
		else
			// 如果不为空，那么就按照想看的页码进行
			page = new Page(totleRecord, pageNum,pageSize);
		List<Site> sites = siteMapper.listLikeByTitle(key, page.getStartindex(), pageSize);
		return sites;
	}
	
}
