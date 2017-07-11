package com.party.domain;

import java.util.Date;

/**
 * 
 * @author Caizhf
 * @date 2017年6月12日下午6:17:56
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 购物清单</p>
 *
 */
public class ShopCart {
	private Integer spid;			//主键
	private String productType;		//商品类型
	private Integer productId;		//商品主键
	private Integer number;			//商品需要的数量
	private String ordersNum;		//订单号
	private Integer ordersState;	//订单状态
	private Date ctime;				//创建时间（只有发送了预约请求才会有时间）
	private User user;				//创建的用户
	private Admin admin;			//商家
	
	public Integer getSpid() {
		return spid;
	}
	public void setSpid(Integer spid) {
		this.spid = spid;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getOrdersNum() {
		return ordersNum;
	}
	public void setOrdersNum(String ordersNum) {
		this.ordersNum = ordersNum;
	}
	public Integer getOrdersState() {
		return ordersState;
	}
	public void setOrdersState(Integer ordersState) {
		this.ordersState = ordersState;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "ShopCart [spid=" + spid + ", productType=" + productType + ", productId=" + productId + ", number="
				+ number + ", ordersNum=" + ordersNum + ", ordersState=" + ordersState + ", ctime=" + ctime + ", user="
				+ user + ", admin=" + admin + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((ctime == null) ? 0 : ctime.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((ordersNum == null) ? 0 : ordersNum.hashCode());
		result = prime * result + ((ordersState == null) ? 0 : ordersState.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		result = prime * result + ((spid == null) ? 0 : spid.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopCart other = (ShopCart) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (ctime == null) {
			if (other.ctime != null)
				return false;
		} else if (!ctime.equals(other.ctime))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (ordersNum == null) {
			if (other.ordersNum != null)
				return false;
		} else if (!ordersNum.equals(other.ordersNum))
			return false;
		if (ordersState == null) {
			if (other.ordersState != null)
				return false;
		} else if (!ordersState.equals(other.ordersState))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		if (spid == null) {
			if (other.spid != null)
				return false;
		} else if (!spid.equals(other.spid))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
