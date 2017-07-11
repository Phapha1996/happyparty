package com.party.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.party.domain.Role;
import com.party.domain.User;
/**
 * 
 * @author Caizhf
 * @date 2017年5月22日下午8:27:56
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: SpringSecurity 的自定义 UserDetails</p>
 *
 */
public class MyUserDetails extends User implements UserDetails{
	
	public  MyUserDetails(){
		super();
	}
	
	public MyUserDetails(User user){
		super(user);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> roles = this.getRoles();
		if (roles == null || roles.size() < 1) {
			return AuthorityUtils.commaSeparatedStringToAuthorityList("");
		}
		StringBuilder commaBuilder = new StringBuilder();
		for (Role role : roles) {
			commaBuilder.append(role.getRoleName()).append(",");
		}
		String authorities = commaBuilder.substring(0,
				commaBuilder.length() - 1);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
