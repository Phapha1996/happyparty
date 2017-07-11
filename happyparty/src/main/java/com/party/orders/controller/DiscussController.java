package com.party.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.domain.Discuss;
import com.party.domain.User;
import com.party.dto.Response;
import com.party.orders.service.DiscussService;

/**
 * 
 * @author Caizhf
 * @date 2017年6月15日下午2:10:51
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 对商品进行评论，注意：只有购买过、并且完成订单的才能进行评价</p>
 *
 */
@RequestMapping("/discuss")
@RestController
public class DiscussController {
	
	@Autowired
	private DiscussService discussService;
	
	/**
	 * 功能：评论
	 * @param discuss 传值：传入productId、productType、content
	 * @return
	 */
	//访问地址：127.0.0.1:8080/discuss/add?productId=*&productType=*&content=*
	@RequestMapping("/add")
	public Response save(Discuss discuss){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		discuss.setUser(user);
		discussService.save(discuss);
		return Response.success();
	}
	
	/**
	 * 功能：查询指定商品下面的所有评价,分页显示
	 * @param productType
	 * @param productId
	 * @return
	 */
	//访问地址：127.0.0.1:8080/discuss/list/*?productType=*&productId=*
	@RequestMapping("/list/{pageNum}")
	public Response listByProduct(@RequestParam("productType") String productType,@RequestParam("productId")int productId,
			@PathVariable int pageNum,@RequestParam("pageSize")int pageSize){
		return Response.success(discussService.listByProduct(productType,productId,pageNum,pageSize));
	}
	
	
}
