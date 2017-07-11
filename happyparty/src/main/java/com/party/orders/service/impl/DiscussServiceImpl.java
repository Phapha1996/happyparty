package com.party.orders.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.party.domain.Discuss;
import com.party.dto.Page;
import com.party.exception.ServiceException;
import com.party.orders.mapper.DiscussMapper;
import com.party.orders.service.DiscussService;

/**
 * 
 * @author Caizhf
 * @date 2017年6月15日下午2:34:21
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 评价业务实现层</p>
 *
 */
@Service
public class DiscussServiceImpl implements DiscussService{
	
	@Autowired
	private DiscussMapper discussMapper;
	
	@Override
	public void save(Discuss discuss) {
		if(isBuyThisProduct(discuss.getProductId(),discuss.getProductType(),discuss.getUser().getId())){
			discuss.setCtime(new Date());
			discussMapper.save(discuss);
			return ;
		}
		throw new ServiceException("您并没有买过此商品!");
	}
	
	/*
	 * 查询某个产品下面所有的评价，分页显示
	 */
	@Override
	public List<Discuss> listByProduct(String productType, int productId, int pageNum, int pageSize) {
		int totleRecord = discussMapper.countListByProductIdAndProductType(productType,productId);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			//如果不为空，那么就按照想看的页码进行
			page = new Page(totleRecord, pageNum,pageSize);
		List<Discuss> discusses = discussMapper.listByProductIdAndProductType(productType, productId,page.getStartindex(),pageSize);
		return discusses;
	}
	
	/*
	 * 判断这个商品是否已经被购买并且订单处于完成的状态，如果完成才允许用户评论
	 * 用做保留接口，想用就用，不用就算了
	 */
	private boolean isBuyThisProduct(int productId,String productType,int userId){
		return true;
	}

}
