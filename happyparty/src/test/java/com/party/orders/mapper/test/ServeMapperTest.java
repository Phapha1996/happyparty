package com.party.orders.mapper.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.party.domain.Serve;
import com.party.product.mapper.ServeMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServeMapperTest {
	@Autowired
	ServeMapper serveMapper;
	
	@Test
	public void listByCategoryIdTest(){
		List<Serve> list = serveMapper.listByCategoryId("达人服务", 1, 0, 5);
		System.out.println(list);
	}
}
