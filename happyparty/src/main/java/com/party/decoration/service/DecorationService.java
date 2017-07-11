package com.party.decoration.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.Decoration;
import com.party.domain.DecorationProduct;
import com.party.domain.Theme;

public interface DecorationService
{
	//分页查询所有主题
	public List<Theme> findAllTheme(Integer page,Integer size);
	
	
	//查询主题下的场地布置
	public List<Decoration> findThemeIsDecoration(Integer thid,Integer page,Integer size);

	//按id查询场地布置详情
	public Decoration findIdDecoration(Integer did);
	
	//查询主题下的所有商品
	public List<DecorationProduct> findByidAllProduct(Integer did,Integer page,Integer size);

	
	//随机返回几条场地布置信息
	List<Decoration> findRandDecoration(Integer thid,Integer size);

	
}
