package com.party.product.controller;

import java.util.List;


import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.party.SMS.ToolSMS;
import com.party.domain.Custom;
import com.party.domain.User;
import com.party.dto.Response;
import com.party.product.service.impl.CustomServiceImpl;

@RestController
@RequestMapping("/custom")
public class CustomController
{
	private final Logger logger = LoggerFactory.getLogger(CustomController.class);
	
	@Autowired
	private CustomServiceImpl customService;
	

	
	
	//添加定制服务
	@RequestMapping(value="addcustom")
	public Response addCustom(Custom custom,HttpSession session)
	{
/*		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		User user = (User) context.getAuthentication().getPrincipal();
		custom.setCu_uid(user.getId());*/
		customService.addCustom(custom);
		return Response.success();
	}
	
	//通知老板用户使用了定制服务
	@RequestMapping(value="informBoos")
	public Response informBoos(String people,String phone,String number)
	{
		String para=people+","+phone+","+number;
		ToolSMS.sendInformSMS(para);
		return Response.success();
	}
	
	//按id查询定制服务
	@RequestMapping(value="/getIdCustom")
	public Response getIdCustom(int cuid)
	{
		Custom custom=customService.findIdCustom(cuid);
		return Response.success(custom);		
	}
	
	
	//按id删除定制服务
	@RequestMapping(value="/deletecustom")
	public Response deleteCustom(int cuid)
	{
		customService.deleteCustom(cuid);
		return Response.success();
	}
	
	
	//分页查询所有定制服务
	@RequestMapping(value="/findallcustom")
	public Response findAllCustom(int page,int size)
	{
		List<Custom> custom=customService.findAllCustom(page, size);
		return Response.success(custom);
	}
	
	
	//按手机号查询所有定制服务
	@RequestMapping(value="/findphonecustom")
	public Response findPhoneCustom(String phone,int page,int size)
	{
		List<Custom> custom=customService.findPhoneCustom(phone, page, size);
		return Response.success(custom);
	}
	
}
