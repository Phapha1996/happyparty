package com.party.domain;

import java.math.BigDecimal;
import java.util.List;

public class Site 
{
	private int siid;					//主键
	private String title;				//标题
	private BigDecimal price;			//价格
	private Double weekPrice;				//周末价格
	private String city;				//城市
	private String address;				//地址
	private int room_num;				//房间数
	private int bed_num;				//床位数
	private int apply;					//适合人数
	private String tags;				//标签
	private String description;			//介绍
	private String facilities;			//配套设施
	private String reference;			//补充介绍
	private String remind;				//温馨提示
	private String wechat;				//微信
	private int num;					//数量
	private int sequence;				//排序
	private int admin_id;				//管理员外键
	private List<Img> imgs;
	
	
	




	public List<Img> getImgs() {
		return imgs;
	}
	public void setImgs(List<Img> imgs) {
		this.imgs = imgs;
	}
	public int getSiid() {
		return siid;
	}
	public void setSiid(int siid) {
		this.siid = siid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getRoom_num() {
		return room_num;
	}
	public void setRoom_num(int room_num) {
		this.room_num = room_num;
	}
	public int getBed_num() {
		return bed_num;
	}
	public void setBed_num(int bed_num) {
		this.bed_num = bed_num;
	}
	public int getApply() {
		return apply;
	}
	public void setApply(int apply) {
		this.apply = apply;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFacilities() {
		return facilities;
	}
	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getRemind() {
		return remind;
	}
	public void setRemind(String remind) {
		this.remind = remind;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public Double getWeekPrice() {
		return weekPrice;
	}
	public void setWeekPrice(Double weekPrice) {
		this.weekPrice = weekPrice;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	
	
}
