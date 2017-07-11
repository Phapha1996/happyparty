package com.party.product.controller;

import java.util.List;


import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.party.domain.Collect;
import com.party.domain.Serve;
import com.party.domain.User;
import com.party.dto.Response;
import com.party.product.service.impl.CollectServiceImpl;



@RestController
@RequestMapping("/collect")
public class CollectController {
private final Logger logger = LoggerFactory.getLogger(CollectController.class);
	
	@Autowired
	private CollectServiceImpl collectService;
	

	
	
	
	
	//增加收藏
	@RequestMapping(value="addCollect")
	public Response addCollect(Collect collect,HttpSession session)
	{
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
	    User user = (User) context.getAuthentication().getPrincipal();
	    
	    boolean c=collectService.addCollect(collect, user.getId());
	    if(c==true)return Response.success("收藏成功");
	    else if(c==false)return Response.failure("该商品您已收藏过啦");
	    
	    return Response.success();
	}
	
	
	//删除收藏接口
	@RequestMapping(value="deleteCollect")
	public Response deleteCollect(int coid)
	{
		collectService.deleteByIdCollect(coid);
		return Response.success();
	}
	
	//获取所有场地类型收藏
	@RequestMapping(value="getCollectSite")
	public Response getCollectSite(int page,int size,HttpSession session)
	{
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
	    User user = (User) context.getAuthentication().getPrincipal();
	    List<Collect> collect=collectService.findCollectBySite(user.getId(),page,size);
	    return Response.success(collect);
	}
	
	//获取所有服务收藏(达人、包车、餐饮)
	@RequestMapping(value="getCollectServe")
	public Response getCollectServe(String type,Integer page,Integer size,HttpSession session)
	{
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
	    User user = (User) context.getAuthentication().getPrincipal();
	    List<Collect> serve=collectService.findTypeServe(user.getId(),type, page, size);
		return Response.success(serve);
	}
	
	
	//按收藏id查询二级服务(达人、包车、餐饮)
	@RequestMapping(value="getIdCollectServe")
	public Response getIdCollectServe(Integer coid)
	{
		Collect collect=collectService.findIdServe(coid);
		return Response.success(collect);
	}
	
	//按收藏id查询场地信息
	@RequestMapping(value="getIdCollectSite")
	public Response getIdCollectSite(Integer coid)
	{
		Collect collect=collectService.findIdSite(coid);
		return Response.success(collect);
	}
	
	//获取所有服务收藏(套餐)
	@RequestMapping(value="getCollectMeal")
	public Response getCollectMeal(String type,Integer page,Integer size,HttpSession session)
	{
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
	    User user = (User) context.getAuthentication().getPrincipal();
	    List<Collect> meal=collectService.findTypeMeal(user.getId(),type, page, size);
		return Response.success(meal);
	}
	
	//按收藏id查询套餐
	@RequestMapping(value="getIdCollectMeal")
	public Response getIdCollectMeal(Integer coid)
	{
		Collect meal=collectService.findIdMeal(coid);
		return Response.success(meal);
	}
	
	
	
}
