package com.party.domain;
/**
 * 
 * @author Caizhf
 * @date 2017年6月11日下午12:10:27
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 服务分类</p>
 *
 */
public class ServeCategory {
	private Integer scid;			//主键
	private String categoryName;	//细分类板块名称
	public Integer getScid() {
		return scid;
	}
	public void setScid(Integer scid) {
		this.scid = scid;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((scid == null) ? 0 : scid.hashCode());
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
		ServeCategory other = (ServeCategory) obj;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (scid == null) {
			if (other.scid != null)
				return false;
		} else if (!scid.equals(other.scid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ServeCategory [scid=" + scid + ", categoryName=" + categoryName + "]";
	}
}
