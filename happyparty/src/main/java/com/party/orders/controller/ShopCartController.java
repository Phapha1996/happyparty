package com.party.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.domain.ShopCart;
import com.party.domain.User;
import com.party.dto.Response;
import com.party.orders.service.ShopBusinessService;

/**
 * 
 * @author Caizhf
 * @date 2017年6月13日下午12:28:21
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 聚会清单控制器</p>
 *
 */
@RequestMapping("/shop")
@RestController
public class ShopCartController {
	
	@Autowired
	private ShopBusinessService shopBusinessService;
	
	/**
	 * 功能：将商品加入清单列表
	 * @param productType 商品类型
	 * @param productId	商品主键
	 * 注意：不需要传入数量，订单号自动设置为空，订单状态自动设置为0，创建时间自动设置
	 * @return 简单200状态码
	 */
	//测试地址：127.0.0.1:8080/shop/add?productType=*&productId=**admin.aid=*
	@RequestMapping("/add")
	public Response save(ShopCart shopCart){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		shopCart.setUser(user);
		shopBusinessService.save(shopCart);
		return Response.success();
	}
	
	/**
	 * 功能：删除清单列表的某个子清单
	 * 限制：接口控制访问：只有state=0才能访问这个接口
	 * @param spid 
	 * @return
	 */
	//测试地址：127.0.0.1:8080/shop/delete/*
	@RequestMapping("/delete/{pid}")
	public Response delete(@PathVariable("pid") int spid){
		shopBusinessService.delete(spid);
		return Response.success();
	}
	
	/**
	 * 功能：清单显示，将那些加入了清单列表的商品分类显示出来
	 * @param pageNum
	 * @param state 这个属性必须是0,Service层自动默认设0
	 * @return 分类的Map，这个Map下面有每个键有一个list，每个list下面有多个商品对象
	 */
	//测试地址：127.0.0.1:8080/shop/list
	@RequestMapping("/list")
	public Response list(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return Response.success(shopBusinessService.listShopCart(user.getId()));
	}
	
	/**
	 * 功能：修改清单中商品所需要的数量
	 * @param spids 需要修改的订单id
	 * @param numbers	需要修改的清单的商品数量
	 * @return
	 */
	//测试地址：127.0.0.1:8080/shop/update?spid=*&number=*
	@RequestMapping("/update")
	public Response updateNumberBySpid(@RequestParam("spid")int spid, @RequestParam("number")int number){
		shopBusinessService.updateNumberBySpid(spid,number);
		return Response.success();
	}
	
	/**
	 * 针对场地布置下商品的专用接口
	 * @param spids
	 * @param numbers
	 * @return
	 */
	@RequestMapping("/add/decpro")
	public Response saveOnDecorationProduct(int[] dpids, int[] numbers,int adminId){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		shopBusinessService.saveOnDecorationProduct(user.getId(),dpids,numbers,adminId);
		return Response.success();
	}
}
