package com.party.orders.service;

import java.util.List;
import java.util.Map;

import com.party.domain.ShopCart;
import com.party.dto.OrdersDTO;
import com.party.dto.Response;

/**
 * 
 * @author Caizhf
 * @date 2017年6月13日下午3:15:36
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 清单、订单的业务层</p>
 *
 */
public interface ShopBusinessService {
	
	/**
	 * 清单的一种状态与转换成订单之后的七种状态
	 */
	static final int SHOP_CART = 0;			//还是清单的状态
	static final int UNPAID = 1;			//待支付
	static final int PEND = 2;				//已支付待处理
	static final int UNUSED = 3;			//未使用
	static final int COMPLETED = 4;			//已完成
	static final int REFUND = 5;			//退款中
	static final int REFUNDED = 6;			//已退款
	static final int CANCELED= 7;			//已取消
	
	
	/**
	 * 将商品加入清单
	 * @param productType 商品类型
	 * @param productId	商品主键
	 * 注意：不需要传入数量，订单号自动设置为空，订单状态自动设置为0，创建时间不设置
	 * @return 简单200状态码
	 */
	void save(ShopCart shopCart);

	/**
	 * 功能：删除清单中的某个记录
	 * 限制：接口控制访问：只有state=0才能访问这个接口
	 * @param spid 
	 * @return
	 */
	void delete(int spid);

	/**
	 * 功能：清单显示，将那些加入了清单的商品进行分类显示出来
	 * @param pageNum
	 * 注意：state 这个属性必须是0,Service层自动默认查询state为0的状态
	 * @return 分类的Map，这个Map下面有每个键有一个list，每个list下面有多个商品对象
	 */
	Map<String,List> listShopCart(int userId);

	/**
	 * 功能：按状态查询订单,将那些成为订单的进行分类显示出来
	 * 注意：state如果为负数，那么就是显示全部而不分类
	 * @param ordersState 状态号
	 * @param pageNum	页码
	 * @param pageSize 一页显示的数据
	 * @return 包装类型对象的集合
	 */
	public Map<String,List> listOrders(int userId, int ordersState, int pageNum,int pageSize);

	/**
	 * 
	 * 功能：显示单个订单的详情
	 * @param spid
	 * @return
	 */
	OrdersDTO findById(int spid);

	/**
	 * 功能：支付接口等等各种接口都需要调用这个接口修改状态
	 * 条件以及跳转的关系：
	 * 		如果支付失败：传入1
	 * 		如果支付成功并且有预约服务：传入2
	 * 		如果预约的服务全部成功：传入3
	 * 		如果支付成功并且没有预约服务：传入3
	 * 		如果订单已经完成：传入4
	 * 		如果正在退款中：传入5
	 * 		如果已经退款：传入6
	 * 		如果已经取消订单：传入7
	 * 
	 * @param state	状态
	 * @param spid 清单的id号码
	 * @return
	 */
	//这是个比较重要的接口
	void updateOnState(int state, int spid);
	
	/**
	 * 功能：结算
	 * @param isSuccess 这个参数后期整合支付功能的时候需要删除,这边传入true就是模拟成功，传入false就是模拟失败
	 */
	void pressOrder(int[] spids,boolean isSuccess);

	
	void updateNumberBySpid(int spid, int number);

	/**
	 * 取消订单
	 * @param spid
	 */
	void updateOnStateToCanceled(int spid);
	
	
	void updateNumbersBySpids(int[] spids, int[] numbers);

	void saveOnDecorationProduct(int userId,int[] dpids, int[] numbers, int adminId);
	
	
}
