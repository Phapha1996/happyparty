package com.party.home.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.party.domain.Play;
import com.party.domain.Recommend;
import com.party.dto.Response;
import com.party.home.service.impl.HomeServiceImpl;
import com.party.tool.UploadUtils;

@RequestMapping("/home")
@RestController
public class HomeController 
{
	private final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private HomeServiceImpl  homeService;
	
	@Value("${play.image.path}")
	private String playImageSavePath;
	
	/*********
	 * 推荐公告模块
	 *  
	 *********/
	
	//添加推荐公告
	@RequestMapping(value="/addRe")
	public Response addRecommends(Recommend recommend)
	{
		homeService.addRecommend(recommend);
		return Response.success();
	}
	
	
	//删除推荐公告(id)
	@RequestMapping(value="deleteRe")
	public Response deleteRecommend(Integer reid)
	{
		homeService.deleteIdRecommend(reid);
		return Response.success();
	}
	
	//查询推荐公告（倒序）
	@RequestMapping(value="findRe")
	public Response findRecommend()
	{
		List<Recommend> recommend=homeService.findByRecommend();
		return Response.success(recommend);
	}
	
	//更新推荐公告
	@RequestMapping(value="updateRe")
	public Response updateRecommend(String title,String url,Integer reid)
	{
		homeService.updateIdRecommend(title, url, reid);
		return Response.success();
	}
	
	//增加轮播
	@RequestMapping(value="addPlay")
	@Transactional
	public Response addPlay(Play play,@RequestParam("image")MultipartFile image) throws Exception
	{
		if(image.isEmpty())
			return Response.failure("图片不能为空");
		String serverPath = UploadUtils.MvcUpload(image, playImageSavePath);
		logger.info("图片："+image.getName()+"<--存储路径为-->"+serverPath);
		play.setImg_url(serverPath);
		homeService.addByPlay(play);
		return Response.success();
	}
	
	//查询轮播（倒序）
	@RequestMapping(value="findPlay")
	public Response findPlay(String type)
	{
		List<Play> play=homeService.findByPlay(type);
		return Response.success(play);
	}
	
	//更新轮播链接
	@RequestMapping(value="updatePlay")
	public Response updatePlay(String url,Integer pid)
	{
		homeService.updateIdPlay(url, pid);
		return Response.success();
	}
	
	//删除轮播(id)
	@RequestMapping(value="deletePlay")
	public Response deletePlay(Integer pid)
	{
		homeService.deleteIdPlay(pid);
		return Response.success();
	}
	
}
