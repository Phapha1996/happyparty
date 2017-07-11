package com.party.product.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.party.domain.Collect;
import com.party.domain.Serve;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
import com.party.product.mapper.CollectMapper;
import com.party.product.service.CollectService;
@Service
public class CollectServiceImpl implements CollectService
{
	private final Logger logger = LoggerFactory.getLogger(CollectServiceImpl.class);
	
	@Autowired
	private CollectMapper collectMapper;
	
	
	//增加收藏信息
	public boolean addCollect(Collect collect,int uid)
	{
		int state=collectMapper.selectProductIsRegister(collect.getProduct_type(), collect.getProduct_id(),uid);
		if(state==0)
		{
			collect.setUser_id(uid);
			int status=collectMapper.insterCollect(collect);
			if(status==0)
				throw new ServiceException("增加收藏失败");
			return true;
		}
		else return false;

	}
	
	//按id删除收藏信息
	public void deleteByIdCollect(int coid)
	{
		if(coid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int status=collectMapper.deleteIdCollect(coid);
		if(status==0)
			throw new ServiceException("删除收藏失败或您已无收藏此商品");
	}
	
	
	
	//查询用户收藏里的场地信息
	public List<Collect> findCollectBySite(int uid,int page,int size)
	{	
		if(uid==0||page==0||size==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int start=size*(page-1);
		List<Collect> collect=collectMapper.findByIdCollectSite(uid,start,size);
		return collect;
	}
	
	
	//查询二级服务类
	public List<Collect> findTypeServe(Integer uid,String type,Integer page,Integer size)
	{
		if(StringUtils.isEmpty(type))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(uid==0||page==0||size==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int start=size*(page-1);
		List<Collect> serve=collectMapper.findByTypeServe(uid,type, start, size);
		return serve;
	}
	
	
	//按收藏id查询二级服务
	public Collect findIdServe(Integer coid)
	{
		if(coid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		Collect collect=collectMapper.findByIdServe(coid);
		if(collect.equals(null))
			throw new ServiceException("查询二级服务失败");
		return collect;
	}
	
	
	//按收藏id查询场地信息
	public Collect findIdSite(Integer coid)
	{
		if(coid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		Collect collect=collectMapper.findByIdSite(coid);
		if(collect.equals(null))
			throw new ServiceException("查询场地信息失败");
		return collect;
	}
	//查询套餐
	public List<Collect> findTypeMeal(Integer uid,String type,Integer page,Integer size)
	{
		if(StringUtils.isEmpty(type))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(uid==0||page==0||size==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int start=size*(page-1);
		List<Collect> meal=collectMapper.findByTypeMeal(uid,type, start, size);
		return meal;
	}
	//按收藏id查询套餐
	public Collect findIdMeal(Integer coid)
	{
		if(coid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		Collect collect=collectMapper.findByIdMeal(coid);
		if(collect.equals(null))
			throw new ServiceException("查询二级服务失败");
		return collect;
	}
}
