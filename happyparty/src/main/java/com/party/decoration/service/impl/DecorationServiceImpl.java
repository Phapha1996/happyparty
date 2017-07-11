package com.party.decoration.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.party.decoration.mapper.DecorationMapper;
import com.party.decoration.service.DecorationService;
import com.party.domain.Decoration;
import com.party.domain.DecorationProduct;
import com.party.domain.Theme;
import com.party.dto.Page;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;

@Service
public class DecorationServiceImpl implements DecorationService
{

	private final Logger logger = LoggerFactory.getLogger(DecorationServiceImpl.class);
	
	@Autowired
	private DecorationMapper decorationMapper;
	
	
	
	//分页查询所有主题
	public List<Theme> findAllTheme(Integer page,Integer size)
	{
		if(page==0||size==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int start=size*(page-1);
		List<Theme> theme=decorationMapper.selectAllTheme(start, size);
		if(theme==null||theme.size()==0)
			throw new ServiceException("当前数据库中还没有任何关于这个分类的记录");
		return theme;
	}
	
	
	
	//查询主题下的场地布置
	public List<Decoration> findThemeIsDecoration(Integer thid,Integer page,Integer size)
	{
		if(thid==0||page==0||size==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int start=size*(page-1);
		List<Decoration> decoration= decorationMapper.selectThemeDecoration(thid, start, size);
		return decoration;
	}
	
	//按id查询场地布置详情
	public Decoration findIdDecoration(Integer did)
	{
		if(did==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		Decoration decoration=decorationMapper.selectByIdDecoration(did);
		if(decoration==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
		return decoration;
	}
	
	
	//查询主题下的所有商品
	public List<DecorationProduct> findByidAllProduct(Integer did,Integer page,Integer size)
	{
		if(did==0||page==0||size==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int start=size*(page-1);
		List<DecorationProduct> dep= decorationMapper.selectIdDecProduct(did, start, size);
		if(dep==null||dep.size()==0)
			throw new ServiceException("当前数据库中还没有任何关于这个分类的记录");
		return dep;
	}
	
	//随机返回几条场地布置信息
	public List<Decoration> findRandDecoration(Integer thid,Integer size)
	{
		if(thid==0||size==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		List<Decoration> decortion=decorationMapper.selectRandDecoration(thid, size);
		if(decortion.equals(null)||decortion.size()==0)
			throw new ServiceException("当前数据库中还没有任何关于这个分类的记录");
		return decortion;
	}



	public List<Decoration> listDecorationLikeByTitle(int pageNum, int pageSize, String key) {
		int totleRecord = decorationMapper.countListDecorationLikeByTitle(key);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord, pageNum,pageSize);
		List<Decoration> decs = decorationMapper.listDecorationLikeByTitle(key,page.getStartindex(),pageSize);
		return decs;
	}
	
	
}
