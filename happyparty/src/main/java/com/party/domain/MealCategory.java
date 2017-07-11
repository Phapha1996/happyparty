package com.party.domain;

import java.util.List;

/**
 * 
 * @author Caizhf
 * @date 2017年7月5日下午2:29:34
 * @version v.0.1
 * @email 1115054416@qq.com
 *
 *        <p>
 * 		Description: 套餐分类
 *        </p>
 *
 */
public class MealCategory {
	private Integer mcid;
	private String categoryName;
	private Integer sequence;
	private List<Img> imgs;

	public Integer getMcid() {
		return mcid;
	}

	public void setMcid(Integer mcid) {
		this.mcid = mcid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public List<Img> getImgs() {
		return imgs;
	}

	public void setImgs(List<Img> imgs) {
		this.imgs = imgs;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	

}
