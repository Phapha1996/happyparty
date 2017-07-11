package com.party.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 
 * @author Caizhf
 * @date 2017年6月10日下午2:48:32
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 用户实体</p>
 *
 */
public class User implements Serializable{
	
	/**
	 * 
	 */
	private Integer id;						//主键
	private String nickname;				//昵称（姓名）
	private String username;				//用户名
	private String password;				//密码
	private String icon;					//头像
	private Integer integral;				//积分
	private Date regdate;					//注册时间
	private Boolean activate; 				//是否激活
	private List<Role> roles;				//用户的角色
	
	public User(User user){
		this.id = user.getId();
		this.nickname = user.getNickname();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.icon = user.getIcon();
		this.integral = user.getIntegral();
		this.regdate = user.getRegdate();
		this.activate = user.getActivate();
		this.roles = user.getRoles();
	}
	
	public User(){
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Boolean getActivate() {
		return activate;
	}
	public void setActivate(Boolean activate) {
		this.activate = activate;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activate == null) ? 0 : activate.hashCode());
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((integral == null) ? 0 : integral.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((regdate == null) ? 0 : regdate.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (activate == null) {
			if (other.activate != null)
				return false;
		} else if (!activate.equals(other.activate))
			return false;
		if (icon == null) {
			if (other.icon != null)
				return false;
		} else if (!icon.equals(other.icon))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (integral == null) {
			if (other.integral != null)
				return false;
		} else if (!integral.equals(other.integral))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (regdate == null) {
			if (other.regdate != null)
				return false;
		} else if (!regdate.equals(other.regdate))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", username=" + username + ", password=" + password
				+ ", icon=" + icon + ", integral=" + integral + ", regdate=" + regdate + ", activate=" + activate
				+ ", roles=" + roles + "]";
	}
	
	public void setPropertiesEmpty(){
		this.password = null;
		this.regdate = null;
		this.activate = null;
		this.roles = null;
	}

}
