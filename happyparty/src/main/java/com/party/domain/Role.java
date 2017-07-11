package com.party.domain;

import java.io.Serializable;

/**
 * 
 * @author Caizhf
 * @date 2017年6月10日下午6:20:07
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 用户角色</p>
 *
 */
public class Role implements Serializable{
	private Integer roid;		//主键
	private String roleName;	//角色名称
	
	public Integer getRoid() {
		return roid;
	}
	public void setRoid(Integer roid) {
		this.roid = roid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roid == null) ? 0 : roid.hashCode());
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
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
		Role other = (Role) obj;
		if (roid == null) {
			if (other.roid != null)
				return false;
		} else if (!roid.equals(other.roid))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Role [roid=" + roid + ", roleName=" + roleName + "]";
	}
	
}