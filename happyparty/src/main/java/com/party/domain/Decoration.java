package com.party.domain;

import java.util.List;

public class Decoration 
{
	private int did;					//主键
	private String title;				//标题
	private String city;				//城市
	private String tags;				//标签
	private String details;				//详细内容
	private int sequence;				//排序
	private int theme_id;				//主题外键
	private int admin_id;				//管理员外键
	private Double price;		//原价
	private Double bottom_price;	//最低价
	private List<Img> imgs;
	
	
	
	

	public List<Img> getImgs() {
		return imgs;
	}
	public void setImgs(List<Img> imgs) {
		this.imgs = imgs;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getTheme_id() {
		return theme_id;
	}
	public void setTheme_id(int theme_id) {
		this.theme_id = theme_id;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getBottom_price() {
		return bottom_price;
	}
	public void setBottom_price(Double bottom_price) {
		this.bottom_price = bottom_price;
	}
	

}
