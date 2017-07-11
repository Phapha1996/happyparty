package com.party.domain;

import java.util.List;
/**
 * 
 * @author Caizhf
 * @date 2017年6月26日下午4:00:47
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 聚会套餐</p>
 *
 */
public class SetMeal {
	private Integer smid;					//主键
	private String title;					//标题
	private Double price;					//价格
	private String city;					//城市
	private String address;					//地址
	private Integer roomNum;				//房间数
	private Integer bedNum;					//床位数
	private Integer apply;					//适合人数
	private String tags;					//标签
	private String description;				//介绍
	private String facilities;				//配套设施
	private String reference;				//补充介绍
	private String remind;					//温馨提示
	private Integer num;					//数量
	private Integer sequence;				//排序
	private Admin admin;					//属于的商家
	private List<Img> imgs;					//图片列表
	private MealCategory mealCategory;		//所属类型
	
	public Integer getSmid() {
		return smid;
	}
	public void setSmid(Integer smid) {
		this.smid = smid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
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
	public Integer getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}
	public Integer getBedNum() {
		return bedNum;
	}
	public void setBedNum(Integer bedNum) {
		this.bedNum = bedNum;
	}
	public Integer getApply() {
		return apply;
	}
	public void setApply(Integer apply) {
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
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
	public MealCategory getMealCategory() {
		return mealCategory;
	}
	public void setMealCategory(MealCategory mealCategory) {
		this.mealCategory = mealCategory;
	}

}
