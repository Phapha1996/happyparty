package com.party.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.domain.User;
import com.party.dto.Response;
import com.party.orders.service.ShopBusinessService;
/**
 * 
 * @author Caizhf
 * @date 2017年6月13日下午4:19:08
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 订单控制器</p>
 *
 */
@RequestMapping("/orders")
@RestController
public class OrdersController {
	
	@Autowired
	private ShopBusinessService shopBusinessService;
	
	
	/**
	 * 功能：删除订单
	 * @param spid 
	 * @return
	 */
	//测试地址：127.0.0.1:8080/shop/delete/*
	@RequestMapping("/delete/{spid}")
	public Response delete(@PathVariable("spid") int spid){
		shopBusinessService.delete(spid);
		return Response.success();
	}
	
	/**
	 * 功能：按状态查询订单
	 * @param ordersState 状态号
	 * @param pageNum	页码
	 * @return 包装类型对象的集合
	 */
	//测试地址：127.0.0.1:8080/orders/list/*?ordersState=*&pageSize=*
	@RequestMapping("/list/{pageNum}")
	public Response list(@RequestParam("ordersState") int ordersState,
			@PathVariable int pageNum,@RequestParam("pageSize") int pageSize){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return Response.success(shopBusinessService.listOrders(user.getId(), ordersState, pageNum, pageSize));
	}
	
	/**
	 * 功能：显示单个订单的详情
	 * @param spid
	 * @return
	 */
	//测试地址：127.0.0.1:8080/orders/get/*
	@RequestMapping("/get/{spid}")
	public Response get(@PathVariable int spid){
		return Response.success(shopBusinessService.findById(spid));
	}
	
	/**
	 * 功能：支付接口等等各种接口都需要调用这个接口修改状态
	 * 请注意：这是一个非常危险的方法，有用户可能会恶意修改订单状态而访问这个接口（上线的时候这个接口有待注释）
	 * 条件以及跳转的关系：
	 * 		如果支付失败：传入1
	 * 		如果支付成功并且有预约服务：传入2
	 * 		如果预约的服务全部成功：传入3
	 * 		如果支付成功并且没有预约服务：传入3
	 * 		如果订单已经完成：传入4
	 * 		如果正在退款中：传入5
	 * 		如果已经退款：传入6
	 * 		如果已经取消订单：传入7
	 * @param state 1~7
	 * @return
	 */
	//测试地址:127.0.0.1:8080/orders/update?state=*&spid=*
	@RequestMapping("/update")
	public Response updateOnState(@RequestParam("state") int state,@RequestParam("spid") int spid){
		shopBusinessService.updateOnState(state,spid);
		return Response.success();
	}
	
	
	/**
	 * 功能：下单
	 * 顺序：点击下单按钮，模拟支付成功、失败两种状态，根据调用方法的返回值判定是成功还是失败
	 * 	       如果失败，就改false，如果成功，就改success
	 * @param spids：清单的id号码
	 * @return
	 */
	//测试地址：127.0.0.1:8080/orders/press?spids[0]=*&spids[1]=*....&isSuccess=*
	@RequestMapping("/press")
	public Response pressOrder(int[] spids,boolean isSuccess){
		shopBusinessService.pressOrder(spids,isSuccess);
		return Response.success();
	}
	
	/**
	 * 功能：取消订单
	 * @param spid
	 * @return
	 */
	//测试地址127.0.0.1:8080/calloff?spid=*
	@RequestMapping("/calloff")
	public Response callOffOrders(@RequestParam("spid") int spid){
		shopBusinessService.updateOnStateToCanceled(spid);
		return Response.success();
	}

}
