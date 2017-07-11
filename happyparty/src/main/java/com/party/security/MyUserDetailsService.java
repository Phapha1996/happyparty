package com.party.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.party.domain.User;
import com.party.user.mapper.SysUserMapper;

/**
 * 
 * @author Caizhf
 * @date 2017年5月22日下午8:31:16
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 自定义SpringSecurity UserDetailsService类</p>
 *
 */
@Service
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	private SysUserMapper mapper;
	private final Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);
	
	/**
	 * 把username返回回去
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("username:" + username + "------------------");
		User user = mapper.findByUsername(username);
		if (user == null)
			throw new BadCredentialsException("用户不存在");
		if(!user.getActivate())
			throw new BadCredentialsException("用户还未激活");
		log.info("username" + user.getUsername() + "|" + "password"
				+ user.getPassword());
		return new MyUserDetails(user);
	}

}
