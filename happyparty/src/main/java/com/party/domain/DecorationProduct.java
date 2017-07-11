package com.party.domain;

import java.util.List;

/**
 * 
 * @author Caizhf
 * @date 2017年6月14日上午11:46:28
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description:场地布置下的商品</p>
 *
 */
public class DecorationProduct {
	private Integer dpid;				//主键
	private String title;				//标题
	private String description;			//描述
	private Integer number;				//数量
	private Double price;				//价格
	private Integer categoryId;			//分类外键
	private Integer decorationId;		//场地布置外键
	private DecorationProductCategory productCatrgory;
	private List<Img> imgs;
	public Img getImg() {
		return img;
	}
	public void setImg(Img img) {
		this.img = img;
	}
	private Img img;
	private Admin admin;
	
	
	
	public DecorationProductCategory getProductCatrgory() {
		return productCatrgory;
	}
	public void setProductCatrgory(DecorationProductCategory productCatrgory) {
		this.productCatrgory = productCatrgory;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getDecorationId() {
		return decorationId;
	}
	public void setDecorationId(Integer decorationId) {
		this.decorationId = decorationId;
	}
	public Integer getDpid() {
		return dpid;
	}
	public void setDpid(Integer dpid) {
		this.dpid = dpid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public List<Img> getImgs() {
		return imgs;
	}
	public void setImgs(List<Img> imgs) {
		this.imgs = imgs;
	}
	
}
