package com.party.domain;

import java.util.List;

/**
 * 
 * @author Caizhf
 * @date 2017年6月11日下午12:10:56
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 服务：包括达人服务、包车服务、餐饮服务</p>
 *
 */
public class Serve {
	private Integer sid;		//主键
	private String type;		//服务类型：达人服务、包车服务、餐饮服务
	private String title;		//标题
	private String city;		//城市
	private Double price;		//价格
	private String details;		//注意事项
	private String notes;		//详情
	private String wechat;		//微信号
	private Integer number;		//提供数量
	private Integer sequence;	//排序号
	private Admin admin;		//商家
	private ServeCategory serveCategory;	//细分类
	private List<Img> imgs;					//图片列表
	
	public List<Img> getImgs() {
		return imgs;
	}
	public void setImgs(List<Img> imgs) {
		this.imgs = imgs;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
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
	public ServeCategory getServeCategory() {
		return serveCategory;
	}
	public void setServeCategory(ServeCategory serveCategory) {
		this.serveCategory = serveCategory;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((sequence == null) ? 0 : sequence.hashCode());
		result = prime * result + ((serveCategory == null) ? 0 : serveCategory.hashCode());
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((wechat == null) ? 0 : wechat.hashCode());
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
		Serve other = (Serve) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (sequence == null) {
			if (other.sequence != null)
				return false;
		} else if (!sequence.equals(other.sequence))
			return false;
		if (serveCategory == null) {
			if (other.serveCategory != null)
				return false;
		} else if (!serveCategory.equals(other.serveCategory))
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (wechat == null) {
			if (other.wechat != null)
				return false;
		} else if (!wechat.equals(other.wechat))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Serve [sid=" + sid + ", type=" + type + ", title=" + title + ", city=" + city + ", price=" + price
				+ ", details=" + details + ", notes=" + notes + ", wechat=" + wechat + ", number=" + number
				+ ", sequence=" + sequence + ", admin=" + admin + ", serveCategory=" + serveCategory + ", imgs=" + imgs
				+ "]";
	}
	
}
