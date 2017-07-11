package com.party.product.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.party.domain.Custom;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
import com.party.product.mapper.CustomMapper;
import com.party.product.service.CustomService;

@Service
public class CustomServiceImpl implements CustomService
{
	private final Logger logger = LoggerFactory.getLogger(CustomServiceImpl.class);
	
	@Autowired
	private CustomMapper customMapper;
	
	
	
	//增加定制服务
	public void addCustom(Custom custom)
	{
		int status=customMapper.insterCustom(custom);
		if(status==0)
			throw new ServiceException("增加定制信息失败");	
	}
	
	
	//id查询定制服务
	public Custom findIdCustom(int cuid) 
	{
		if(cuid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		Custom custom=customMapper.selectIdCustom(cuid);
		if(custom.equals(null))
			throw new ServiceException("查询定制信息失败");
		return custom;
	}
	
	
	//按id删除定制服务
	public void deleteCustom(int cuid)
	{
		if(cuid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int status=customMapper.deleteCustoms(cuid);
		if(status==0)
			throw new ServiceException("删除定制信息失败");	
	}
	
	
	//分页查询所有定制服务
	public List<Custom> findAllCustom(int page,int size)
	{
		if(page==0||size==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int start=size*(page-1);
		List<Custom> custom=customMapper.selectAllCustom(start, size);
		if(custom.equals(null))
			throw new ServiceException("查询所有定制信息失败");	
		
		return custom;
	}
	
	
	
	//按手机号查询所有定制服务
	public List<Custom> findPhoneCustom(String phone,int page,int size)
	{
		if(StringUtils.isEmpty(phone))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(page==0||size==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int start=size*(page-1);
		List<Custom> custom=customMapper.selectPhoneCustom(phone, start, size);
		if(custom.equals(null))
			throw new ServiceException("手机号查询定制信息失败");
		return custom;
	}
	
	
	
}
