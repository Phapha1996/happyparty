package com.party.domain;

import java.util.Date;

public class Signin 
{
	private int siid;					//签到表主键id
	private Date sign_time;				//签到时间
	private int user_id;				//用户外键
	private int count;					//签到次数
	
	
	
	public int getSiid() {
		return siid;
	}
	public void setSiid(int siid) {
		this.siid = siid;
	}
	public Date getSign_time() {
		return sign_time;
	}
	public void setSign_time(Date sign_time) {
		this.sign_time = sign_time;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
