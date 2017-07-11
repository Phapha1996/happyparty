package com.party.decoration.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.decoration.service.impl.DecorationServiceImpl;
import com.party.domain.Admin;
import com.party.domain.Decoration;
import com.party.domain.DecorationProduct;
import com.party.domain.Role;
import com.party.domain.Theme;
import com.party.dto.Response;



@RestController
@RequestMapping("/decoration")
public class DecorationController 
{
private final Logger logger = LoggerFactory.getLogger(DecorationController.class);
	
	@Autowired
	private DecorationServiceImpl  decorationService;
	

	
	
	
	//分页查询所有主题
	@RequestMapping(value="getAllTheme")
	public Response getAllTheme(Integer page,Integer size)
	{
		List<Theme> theme=decorationService.findAllTheme(page, size);
		return Response.success(theme);
	}
	
	//获取主题下的所有场地布置
	@RequestMapping(value="getThemeDecortion")
	public Response getThemeDecortion(Integer thid,Integer page,Integer size)
	{
		List<Decoration> decora=decorationService.findThemeIsDecoration(thid, page, size);
		return Response.success(decora);
	}
	
	
	//按id查询场地布置详情
	@RequestMapping(value="getIdDecoration")
	public Response getIdDecoration(Integer did)
	{
		Decoration decoration=decorationService.findIdDecoration(did);
		return Response.success(decoration);
	}
	
	//获取主题下的所有商品
	@RequestMapping(value="getIdDepProduct")
	public Response getIdDepProduct(Integer did,Integer page,Integer size)
	{
		List<DecorationProduct> dep=decorationService.findByidAllProduct(did, page, size);
		return Response.success(dep);
	}
	
	//随机获取几条场地布置信息
	@RequestMapping(value="getRandDecoration")
	public Response getRandDecoration(Integer thid,Integer size)
	{
		List<Decoration> decoration=decorationService.findRandDecoration(thid, size);
		return Response.success(decoration);
	}
	
	/**
	 * 功能：按标题查询场地布置
	 * @param pageNum
	 * @param pageSize
	 * @param key
	 * @return
	 */
	//127.0.0.1:8090/decoration/list/like/*?pageSize=*&key=*
	@RequestMapping("/list/like/{pageNum}")
	public Response listLikeByTitle(@PathVariable int pageNum, 
			@RequestParam("pageSize") int pageSize,@RequestParam("key") String key){
		return Response.success(decorationService.listDecorationLikeByTitle(pageNum,pageSize,key));
	}
	
	
}
