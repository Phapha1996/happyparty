package com.party.product.controller;

import java.util.List;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.domain.Site;
import com.party.dto.Response;
import com.party.product.service.impl.SiteServiceImpl;


@RestController
@RequestMapping("/site")
public class SiteController 
{
	private final Logger logger = LoggerFactory.getLogger(SiteController.class);
	
	@Autowired
	private SiteServiceImpl siteService;
	

	
	
	
	
	
	//获取所有场地
	@RequestMapping(value="getAllSite")
	public Response getAllSite(int page,int size)
	{
		List<Site> site=siteService.findAllSite(page, size);
		return Response.success(site);
	}
	
	//按地区、价格、聚会人数模糊查询场地
	@RequestMapping(value="getLikeSite")
	public Response getLikeSite(String city,Integer startValue,Integer endValue,Integer startApply,Integer endApply,int page,int size)
	{
		List<Site> site=siteService.findLikeSite(city, startValue, endValue, startApply, endApply, page, size);
		return Response.success(site);
	}
	
	
	@RequestMapping(value="getSite")
	public Response getSite(int siid)
	{
		Site site=siteService.findBySite(siid);
		return Response.success(site);
	}
	
	
	@RequestMapping(value="getRandomSite")
	public Response getRandomSite(int number)
	{
		List<Site> site=siteService.findRandomSite(number);
		return Response.success(site);
	}
	
	/**
	 * 功能：按标题模糊查询
	 * @param key
	 * @return
	 */
	//127.0.0.1:8090/site/list/like/*?key=*&pageSize=*
	@RequestMapping("/list/like/{pageNum}")
	public Response listLikeByTitle(@RequestParam("key")String key,
			@PathVariable int pageNum,@RequestParam("pageSize")int pageSize){
		return Response.success(siteService.listLikeByTitle(key,pageNum,pageSize));
	}

}
