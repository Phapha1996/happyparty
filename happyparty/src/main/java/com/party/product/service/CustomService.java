package com.party.product.service;

import java.util.List;

import com.party.domain.Custom;

public interface CustomService 
{
	//添加定制服务
	public void addCustom(Custom custom);
	
	//id查询定制服务
	public Custom findIdCustom(int cuid) ;
	
	//按id删除定制服务
	public void deleteCustom(int cuid);
	
	//分页查询所有定制服务
	public List<Custom> findAllCustom(int page,int size);
	
	//按手机号查询所有定制服务
	public List<Custom> findPhoneCustom(String phone,int page,int size);
}
