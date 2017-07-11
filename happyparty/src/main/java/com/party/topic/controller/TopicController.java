package com.party.topic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.domain.Reply;
import com.party.domain.Topic;
import com.party.domain.User;
import com.party.dto.Response;
import com.party.topic.service.TopicService;
/**
 * 
 * @author Caizhf
 * @date 2017年6月10日下午10:19:20
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 帖子与回复的控制器</p>
 *
 */
@RestController
@RequestMapping("/topic")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	/**
	 * 发帖
	 * @param topic
	 * @return
	 */
	//访问路径:127.0.0.1:8080/topic/add?title=*&content=*
	/*@RequestMapping("/add")
	public Response saveTopic(Topic topic){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		topic.setUser(user);
		return Response.success(topicService.saveTopic(topic));
	}*/
	
	/**
	 * 回复某个帖子
	 * @param reply
	 * @return
	 */
	@RequestMapping("/reply/add")
	//访问路径:127.0.0.1:8080/topic/reply/add?content=*&topic.tid=*
	public Response saveReply(Reply reply){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		reply.setUser(user);
		return Response.success(topicService.saveReply(reply));
	}
	
	/**
	 * 获取帖子列表
	 * @return
	 */
	//访问路径：127.0.0.1:8080/topic/list
	@RequestMapping("/list/{pageNum}")
	public Response listTopic(@PathVariable int pageNum){
		return Response.success(topicService.listTopic(pageNum));
	}
	
	/**
	 * 获取单个帖子的信息
	 * @param tid
	 * @return
	 */
	//访问路径：127.0.0.1:8080/topic/get/*
	@RequestMapping("/get/{tid}")
	public Response getTopic(@PathVariable int tid){
		return Response.success(topicService.getTopic(tid));
	}
	
	/**
	 * 用户删除自己的帖子
	 * @return
	 *//*
	//访问路径：127.0.0.1:8080/topic/delete/*
	@RequestMapping("/delete/{tid}")
	public Response deleteTopic(@PathVariable int tid){
		topicService.deleteTopic(tid);
		return Response.success();
	}*/
	
	/**
	 * 根据帖子id查所有的回复
	 * @param tid
	 * @return
	 */
	//访问路径：127.0.0.1::8080/topic/reply/list/*?pageNum=*
	@RequestMapping("/reply/list/{tid}")
	public Response listReplyByTopicId(@PathVariable int tid,@RequestParam("pageNum")int pageNum){
		return Response.success(topicService.listReplyByTopicId(tid,pageNum));
	}
	
	/**
	 * 获取回复详情
	 * @param rid
	 * @return
	 */
	//访问路径：127.0.0.1:8080/topic/reply/get/*
	@RequestMapping("/reply/get/{rid}")
	public Response getReply(@PathVariable int rid){
		return Response.success(topicService.getReply(rid));
	}
}
