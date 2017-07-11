package com.party.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.domain.Serve;
import com.party.dto.Response;
import com.party.product.service.ServeService;
/**
 * 
 * @author Caizhf
 * @date 2017年6月11日下午12:23:31
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 达人服务、包车服务、餐饮服务共同统称为二级服务</p>
 *
 */
@RestController
@RequestMapping("/serve")
public class ServeController {
	
	@Autowired
	private ServeService serveService;
	
	/**
	 * 显示全部服务，需要传入参数的可选类型：包车服务、达人服务、餐饮服务
	 * @param type 包车服务、达人服务、餐饮服务三选一
	 * @param pageNum 页码
	 * @return
	 */
	//访问路径：127.0.0.1:8080/serve/list/*?type=*				其中第二个*只能是达人服务或者包车服务或者餐饮服务
	@RequestMapping("/list/{pageNum}")
	public Response list(@PathVariable int pageNum,@RequestParam("type")String type){
		return Response.success(serveService.listServe(pageNum,type));
	}

	/**
	 * 显示某个细分类下的所有服务
	 * @param pageNum页码
	 * @param type 包车服务、达人服务、餐饮服务三选一
	 * @param categoryId 细分板块的id号码
	 * @return
	 */
	//访问路径：127.0.0.1:8080/serve/list/category/*?type=*&categoryId=*
	@RequestMapping("/list/category/{pageNum}")
	public Response listServeByCategory(@PathVariable int pageNum,@RequestParam("type")String type,
			@RequestParam int categoryId){
		return Response.success(serveService.listServeByCategory(pageNum,type,categoryId));
	}
	
	/**
	 * 
	 * @param sid 服务id号码
	 * @return
	 */
	//访问路径：127.0.0.1:8080/serve/get/*
	@RequestMapping("/get/{sid}")
	public Response get(@PathVariable int sid){
		return Response.success(serveService.getServe(sid));
	}
	
	/**
	 * 根据类型查看所有的分类
	 * @param type 包车服务、达人服务、餐饮服务三选一
	 * @return
	 */
	//访问路径：127.0.0.1:8080/serve/catlist?type=*
	@RequestMapping("/catlist")
	public Response listCategory(@RequestParam("type") String type){
		return Response.success(serveService.listCategory(type));
	}
	
	/**
	 * 功能：根据标题模糊查询
	 * @param pageNum 页码
	 * @param pageSize 每页需要显示多少条数据
	 * @param type 服务类型
	 * @param key 模糊条件
	 * @return
	 */
	//127.0.0.1:8080/serve/list/like/*?pageSize=*&type=*&key=*
	@RequestMapping("/list/like/{pageNum}")
	public Response listLikeByTitle(@PathVariable int pageNum, 
			@RequestParam("pageSize")int pageSize, @RequestParam("key")String key){
		return Response.success(serveService.listLikeByTitle(pageNum,pageSize, key));
	}
}
