package com.party.product.controller.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.party.orders.controller.OrdersController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersControllerTest {
	@Autowired
	OrdersController c;
	
	@Test
	public void pressTest(){
		int [] spids = {2,3,4};
		c.pressOrder(spids, true);
	}
}
