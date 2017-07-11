package com.party.domain;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Caizhf
 * @date 2017年6月10日下午9:39:10
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 发帖</p>
 *
 */
public class Topic {
	
	private Integer tid;					//主键
	private String title;					//发帖标题
	private Date cTime;						//发帖时间
	private String content;					//发帖内容
	private String clickLike;				//点赞
	private String sequence;				//排序
	private Admin admin;					//发帖的商家
	private List<Img> imgs;
	
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getcTime() {
		return cTime;
	}
	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getClickLike() {
		return clickLike;
	}
	public void setClickLike(String clickLike) {
		this.clickLike = clickLike;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
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
	
}
