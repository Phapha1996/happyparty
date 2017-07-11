package com.party.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.dto.Response;
import com.party.product.service.SetMealService;
/**
 * 
 * @author Caizhf
 * @date 2017年6月26日下午4:02:35
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 聚会套餐</p>
 *
 */
@RequestMapping("/meal")
@RestController
public class SetMealController {
	@Autowired
	private SetMealService service;
	
	/**
	 * 功能：查看所有
	 * 注意：如果传入了类型，那么就是点击图标得到的列表；如果没有传入类型，那么就是显示所有的套餐（所有分类）
	 * @param pageNum
	 * @param categoryId
	 * @return
	 */
	//显示所有套餐：127.0.0.1:8080/meal/list/*?pageSize=*
	//显示图标内的套餐：127.0.0.1:8080/meal/list/*?categoryId=*&pageSize=*
	@RequestMapping("/list/{pageNum}")
	public Response list(@PathVariable int pageNum,
			Integer categoryId,@RequestParam("pageSize") int pageSize){
		return Response.success(service.list(pageNum,categoryId,pageSize));
	}
	
	/**
	 * 功能：查看详情
	 * @param smid
	 * @return
	 */
	//127.0.0.1:8080/meal/get/*
	@RequestMapping("/get/{smid}")
	public Response get(@PathVariable int smid){
		return Response.success(service.findById(smid));
	}
	
	/**
	 * 随机n条
	 * @param num
	 * @return
	 */
	//127.0.0.1:8080/meal/list/random/*
	@RequestMapping("/list/random/{num}")
	public Response random(@PathVariable int num){
		return Response.success(service.listByRandomNum(num));
	}
	
	//127.0.0.1:8080/meal/list/like/*?pageSize=*&key=*
	@RequestMapping("/list/like/{pageNum}")
	public Response listLikeByTitle(@PathVariable int pageNum,@RequestParam int pageSize,@RequestParam String key){
		return Response.success(service.listLikeByTitle(pageNum,pageSize,key));
	}
	
	//----------------------------------------------------Category start-----------------------------------//
	
	/**
	 * 查询单条
	 * @param mcid
	 * @return
	 */
	@RequestMapping("/cat/get/{mcid}")
	public Response getCategory(@PathVariable int mcid){
		return Response.success(service.findCategoryById(mcid));
	}
	
	/**
	 * 查询列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	//127.0.0.1:8090/meal/cat/list/*?pageSize=*
	@RequestMapping("/cat/list/{pageNum}")
	public Response listCategory(@PathVariable int pageNum,@RequestParam int pageSize){
		return Response.success(service.listCategory(pageNum,pageSize));
	}

	
}
