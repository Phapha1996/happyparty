package com.party;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.party.domain.User;
import com.party.user.mapper.SysUserMapper;
import com.party.user.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoringPartyApplicationTests {
	@Autowired
	SysUserMapper mapper;
	
	@Test
	public void contextLoads() {
		User u = mapper.findByUsername("18778381360");
		System.out.println(u.getUsername());
	}

}
