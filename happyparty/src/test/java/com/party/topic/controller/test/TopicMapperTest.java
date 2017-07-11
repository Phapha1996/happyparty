package com.party.topic.controller.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.party.topic.mapper.TopicMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicMapperTest {
	@Autowired
	TopicMapper topicMapper;
	
	@Test
	public void testDeleteById(){
		//int status = topicMapper.deleteById(5);
		//System.out.println(status);
	}
}
