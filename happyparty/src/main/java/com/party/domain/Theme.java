package com.party.domain;

public class Theme 
{
	private int thid;						//主键
	private String theme_name;				//主题名
	private Integer sequence;
	private Img img;
	
	

	public Img getImg() {
		return img;
	}
	public void setImg(Img img) {
		this.img = img;
	}
	public int getThid() {
		return thid;
	}
	public void setThid(int thid) {
		this.thid = thid;
	}
	public String getTheme_name() {
		return theme_name;
	}
	public void setTheme_name(String theme_name) {
		this.theme_name = theme_name;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
}
