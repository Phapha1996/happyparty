package com.party.domain;

import java.util.List;

public class Collect 
{
	private int coid;
	private String product_type;
	private int product_id;
	private int user_id;
	private List<Img> imgs;
	private Site site;
	private Serve serve;
	private SetMeal setMeal;
	
	
	
	public SetMeal getSetMeal() {
		return setMeal;
	}
	public void setSetMeal(SetMeal setMeal) {
		this.setMeal = setMeal;
	}
	public Serve getServe() {
		return serve;
	}
	public void setServe(Serve serve) {
		this.serve = serve;
	}
	public List<Img> getImgs() {
		return imgs;
	}
	public void setImgs(List<Img> imgs) {
		this.imgs = imgs;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public int getCoid() {
		return coid;
	}
	public void setCoid(int coid) {
		this.coid = coid;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	
}
