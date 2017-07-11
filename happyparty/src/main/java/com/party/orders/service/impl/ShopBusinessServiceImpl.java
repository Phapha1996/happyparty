package com.party.orders.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.party.decoration.mapper.DecorationMapper;
import com.party.domain.DecorationProduct;
import com.party.domain.Serve;
import com.party.domain.ShopCart;
import com.party.domain.Site;
import com.party.dto.OrdersDTO;
import com.party.dto.Page;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
import com.party.orders.mapper.ShopCartMapper;
import com.party.orders.service.ShopBusinessService;
import com.party.product.mapper.ServeMapper;
import com.party.product.mapper.SiteMapper;
import com.party.tool.OrdersNumUtils;
/**
 * 
 * @author Caizhf
 * @date 2017年6月13日下午7:28:23
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 购物这一整块专用的service层：包括清单、订单、支付等等业务</p>
 *
 */
@Service
public class ShopBusinessServiceImpl implements ShopBusinessService{
	
	@Autowired
	private ShopCartMapper shopCartMapper;
	@Autowired
	private SiteMapper siteMapper;
	@Autowired
	private ServeMapper serveMapper;
	@Autowired
	private DecorationMapper decMapper;
	
	private final Logger log = LoggerFactory.getLogger(ShopBusinessServiceImpl.class);
	
	private static final String TALENT_SERVICE = "达人服务";
	private static final String BUS_SERVICE = "包车服务";
	private static final String CATER_SERVICE = "餐饮服务";
	private static final String SITE = "场地";
	private static final String DEC_PRODUCT = "场地布置";
	private static final int PRODUCT_NEED_NUM = 1;
	private static final int PAGE_SIZE = 10;
			
		
	//加入清单
	@Override
	@Transactional
	public void save(ShopCart shopCart) {
		if(StringUtils.isEmpty(shopCart.getProductType())||shopCart.getProductId()==0||shopCart.getAdmin().getAid()==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		shopCart.setOrdersState(SHOP_CART);
		ShopCart sp = shopCartMapper.findByShopCartMsg(shopCart);
		//如果用户第一次点击添加，就正常save
		if(sp==null){
			shopCart.setNumber(PRODUCT_NEED_NUM);
			if(shopCartMapper.save(shopCart)==null)
				throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
		}else{
		//如果用户多次点击，那么就修改信息即可
			int newNumber = sp.getNumber() + 1;
			shopCartMapper.updateNumberById(sp.getSpid(),newNumber);
		}
	}

	//删除清单中的某项
	@Override
	@Transactional
	public void delete(int spid) {
		if(spid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		//如果这个清单的状态不是0，不允许删除
		ShopCart shopCart = shopCartMapper.findById(spid);
		if(shopCart.getOrdersState()!=0)
			throw new ServiceException("这个条例不能删除，它已经不是清单的状态了");
		if(shopCartMapper.deleteById(spid)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Map<String, List> listShopCart(int userId) {
		Map<String,List> showPage= new HashMap<String,List>();
		//查出用户所有加入过清单的场地
		List<OrdersDTO> sites =  shopCartMapper.listSiteShopCartByUserIdAndState(userId,SHOP_CART,null,null);
		//查出用户所有加入过清单的达人服务
		List<OrdersDTO> talents = shopCartMapper.listServeShopCartByUserIdAndStateAndServeType(userId,SHOP_CART,TALENT_SERVICE,null,null);
		//查出用户所有加入过清单的包车服务
		List<OrdersDTO> buses = shopCartMapper.listServeShopCartByUserIdAndStateAndServeType(userId,SHOP_CART,BUS_SERVICE,null,null);
		//查出用户所有加入过清单的餐饮服务
		List<OrdersDTO> caters = shopCartMapper.listServeShopCartByUserIdAndStateAndServeType(userId,SHOP_CART,CATER_SERVICE,null,null);
		////查出用户所有加入过清单的场地布置商品
		List<OrdersDTO> decorationProducts = shopCartMapper.listDecProductShopCartByUserIdAndState(userId,SHOP_CART,null,null);
		
		showPage.put(SITE, sites);
		showPage.put(DEC_PRODUCT, decorationProducts);
		showPage.put(CATER_SERVICE, caters);
		showPage.put(TALENT_SERVICE, talents);
		showPage.put(BUS_SERVICE, buses);
		
		return showPage;
	}

	@Override
	public Map<String,List> listOrders(int userId, int ordersState, int pageNum,int pageSize) {
		if(ordersState < 1)
			throw new ServiceException("不是订单状态!");
		int totleRecord = shopCartMapper.countByStateAndUserId(userId,ordersState);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			//如果不为空，那么就按照想看的页码进行
			page = new Page(totleRecord, pageNum,pageSize);
		
		Map<String,List> showPage= new HashMap<String,List>();
		//查出用户所有加入过清单的场地
		List<OrdersDTO> sites =  shopCartMapper.listSiteShopCartByUserIdAndState(userId,
				ordersState,page.getStartindex(),pageSize);
		//查出用户所有加入过清单的达人服务
		List<OrdersDTO> talents = shopCartMapper.listServeShopCartByUserIdAndStateAndServeType(userId,
				ordersState,TALENT_SERVICE,page.getStartindex(),pageSize);
		//查出用户所有加入过清单的包车服务
		List<OrdersDTO> buses = shopCartMapper.listServeShopCartByUserIdAndStateAndServeType(userId,
				ordersState,BUS_SERVICE,page.getStartindex(),pageSize);
		//查出用户所有加入过清单的餐饮服务
		List<OrdersDTO> caters = shopCartMapper.listServeShopCartByUserIdAndStateAndServeType(userId,
				ordersState,CATER_SERVICE,page.getStartindex(),pageSize);
		////查出用户所有加入过清单的场地布置商品
		List<OrdersDTO> decorationProducts = shopCartMapper.listDecProductShopCartByUserIdAndState(userId,
				ordersState,page.getStartindex(),pageSize);
		
		showPage.put(SITE, sites);
		showPage.put(DEC_PRODUCT, decorationProducts);
		showPage.put(CATER_SERVICE, caters);
		showPage.put(TALENT_SERVICE, talents);
		showPage.put(BUS_SERVICE, buses);
		
		return showPage;
	}

	@Override
	public OrdersDTO findById(int spid) {
		if(spid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		OrdersDTO orders = shopCartMapper.findById(spid);
		if(orders==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
		
		if(orders.getProductType().equals(SITE)){
			//如果是场地
			orders.setProduct(siteMapper.findByIdSite(orders.getProductId()));
		}else if(orders.getProductType().equals(DEC_PRODUCT)){
			//如果是场地布置
			orders.setProduct(decMapper.findByProductId(orders.getProductId()));
		}else{
			//不然就是二级服务了
			orders.setProduct(serveMapper.findById(orders.getProductId()));
		}
			
		return orders;
	}

	@Override
	public void updateOnState(int state, int spid) {
		if(spid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		shopCartMapper.updateStateById(state,spid);
	}

	
	/**
	 * 功能：结算,一键下单
	 * @param isSuccess 这个参数后期整合支付功能的时候需要删除,这边传入true就是模拟成功，传入false就是模拟失败
	 */
	@Override
	@Transactional
	public void pressOrder(int[] spids,boolean isSuccess) {
		if(spids==null)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		List<ShopCart> shopCarts = shopCartMapper.findByIds(spids);
		double allPrice = 0;
		//得到结算总价
		for(ShopCart sc:shopCarts){
			double price = shopCartMapper.findProductPriceByProductIdAndProductType(sc.getProductId(),sc.getProductType());
			allPrice += price*sc.getNumber();
		}
		//如果支付成功
		if(pay(isSuccess, allPrice)==true){
			Date date = new Date();
			//因为支付成功，所以需要设置创建时间、生成订单号
			for(ShopCart sc:shopCarts){
				sc.setCtime(date);
				//模拟生成订单号
				sc.setOrdersNum(OrdersNumUtils.toOrdersNum(date));
				
				//如果支付成功并且没有预约的项目,就把用户那条相应的清单状态修改成3
				if(!sc.getProductType().equals(SITE)){
					sc.setOrdersState(UNUSED);
					shopCartMapper.updateStateAndNumAndTimeById(sc);
				}
				else{
				//如果支付成功并且有预约的项目，就把那条相应的清单状态改成2
					sc.setOrdersState(PEND);
					shopCartMapper.updateStateAndNumAndTimeById(sc);
				}
			}
		}else{
			//如果支付失败，就把用户所有为清单状态（0状态）的改成1状态
			shopCartMapper.updateStateByIds(UNPAID,spids);
		}
	}
	
	
	//支付成功或者失败预保留的接口
	private boolean pay(boolean isSuccess,double allPrice){
		log.info("您总共需要支付"+allPrice+"元");
		return isSuccess;
	}

	//修改商品所需数量
	@Override
	public void updateNumberBySpid(int spid, int number){
		shopCartMapper.updateNumberBySpid(spid, number);
	}

	//取消订单
	@Override
	public void updateOnStateToCanceled(int spid) {
		shopCartMapper.updateStateById(CANCELED, spid);
	}

	//批处理
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateNumbersBySpids(int[] spids, int[] numbers) {
		if(spids==null||numbers==null||spids.length!=numbers.length)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		for(int i=0;i<spids.length;i++){
			if(shopCartMapper.updateNumberBySpid(spids[i], numbers[i])==null)
				throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
		}
	}

	//场地布置下的商品下单
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveOnDecorationProduct(int userId,int[] dpids, int[] numbers, int adminId) {
		if(dpids==null||numbers==null||adminId==0||dpids.length!=numbers.length)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		for(int i=0;i<dpids.length;i++){
			if(shopCartMapper.saveOnDecorationProduct(userId,dpids[i],numbers[i],adminId,SHOP_CART)==null)
				throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
		}
	}
	
}
