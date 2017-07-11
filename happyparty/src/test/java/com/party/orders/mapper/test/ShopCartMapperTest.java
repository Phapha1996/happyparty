package com.party.orders.mapper.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.party.domain.User;
import com.party.orders.mapper.ShopCartMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopCartMapperTest {
	@Autowired
	ShopCartMapper mapper;

	@Test
	public void findProductPriceByProductIdAndProductTypeTest(){
		double d = mapper.findProductPriceByProductIdAndProductType(1,"场地布置");
		System.out.println(d);
	}
	
	@Test
	public void updateStateByIdsTest(){
		int[] ids = {1,2,3};
		mapper.updateStateByIds(2, ids);
	}
}
