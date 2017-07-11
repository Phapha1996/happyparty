package com.party.dto;

import com.party.domain.Img;
import com.party.domain.Serve;
import com.party.domain.ShopCart;
import com.party.domain.Site;
/**
 * 
 * @author Caizhf
 * @date 2017年6月13日下午3:54:28
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 订单传输对象，这里面存储了一些需要显示的字段</p>
 *
 */
public class OrdersDTO extends ShopCart{
	private String title;		//商品标题
	private Double price;		//商品价格
	private Double subtotal;	//购买总价
	private Integer hasNumber;	//拥有数量
	private Img img;			//商品图片
	private Object product;		//商品对象本身
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Img getImg() {
		return img;
	}
	public void setImg(Img img) {
		this.img = img;
	}
	public Object getProduct() {
		return product;
	}
	public void setProduct(Object product) {
		this.product = product;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Integer getHasNumber() {
		return hasNumber;
	}
	public void setHasNumber(Integer hasNumber) {
		this.hasNumber = hasNumber;
	}
	
}
