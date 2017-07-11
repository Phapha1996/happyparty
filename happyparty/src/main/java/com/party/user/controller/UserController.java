package com.party.user.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;


import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.party.SMS.ToolSMS;
import com.party.domain.Signin;
import com.party.domain.User;
import com.party.dto.Response;
import com.party.tool.TOTP;
import com.party.user.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserServiceImpl  userService;
	

	
	@Value("${USER_ICON}")
	String USER_ICON;
	
	//发送短信并获取生成秘钥
	@RequestMapping("getSecretKey")
	public Response getSecretKey(String phone)
	{
		//生成秘钥
		String key=TOTP.getRandomNumber(8);
		//用秘钥生成唯一口令
		String totp=TOTP.getTOTP(key);
		//发送验证码短信
		ToolSMS.sendVerifySMS(totp,phone);
		return Response.success(key);
	}
	
	
	//短信验证码来增加用户
	@RequestMapping(value="verifyAddUser")
	public Response verifyAddUser(User user,String verify,String seed)
	{
		if(ToolSMS.sendTOTPSMS(seed, verify))
		{
			userService.addUser(user);
		}
		else return Response.failure("验证码错误，请重试");
		return Response.success();
	}
	
	//发送邮件增加用户
	@RequestMapping(value="addUser")
	public Response addUser(User user)
	{
		if(userService.sendRegisterEmail(user.getUsername()).equals("success"))
		{
			userService.addUser(user);
		}
		else return Response.failure("发送邮件失败，请重试");
		return Response.success();
	}
	 
	//用户激活
	@RequestMapping(value="activate")
	public Response Activate(String userName)
	{
		userService.activateUser(userName);
		return Response.success("用户已成功激活");
	}
	
	//检测用户名是否被注册
	@RequestMapping(value="userNameIsNo")
	public Response UserNameIsNo(String userName)
	{
		boolean u=userService.DetectionUserName(userName);
		if(u==true)return Response.success("该用户名可以使用");
		else if(u==false) return Response.failure("该用户名已被注册");
		
		return Response.success("该用户名可以使用");
	}
	
	//发送邮件修改密码
	@RequestMapping(value="changePasswordEmail")
	public Response changgePasswordEmail(String Password,HttpSession session)
	{
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
	    User user = (User) context.getAuthentication().getPrincipal();
		if(userService.sendPasswordEmail(user.getUsername(), Password).equals("success"))
		{
			return Response.success("修改密码邮件已发送");	
		}
		return Response.failure("修改密码邮件发送失败，请重试");
	}
	
	//修改密码
	@RequestMapping(value="changePassword")
	public Response changePassword(String userName,String Password)
	{
		userService.ChangeUserPassword(userName, Password);
		return Response.success("修改密码成功");
	}

	//验证码修改密码
	@RequestMapping(value="verifyChangePassword")
	public Response verifyChangePassword(String verify,String seed,String Password,HttpSession session)
	{
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
	    User user = (User) context.getAuthentication().getPrincipal();
		if(ToolSMS.sendTOTPSMS(seed, verify))
		{
			userService.ChangeUserPassword(user.getUsername(), Password);
		}else return Response.failure("验证码错误，请重试");
		return Response.success("修改密码成功");
	}
	
	//修改用户姓名
	@RequestMapping(value="changeNickName")
	public Response changeNickName(String nickName,HttpSession session)
	{
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
	    User user = (User) context.getAuthentication().getPrincipal();
		userService.ChangeUserNickName(user.getUsername(), nickName);
		return Response.success();
	}
	
	//用户信息
	@RequestMapping(value="/userMessage")
	public Response UserMessage(HttpSession session)
	{
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		User user = (User) context.getAuthentication().getPrincipal();
		User users=userService.getUserMessage(user.getId());
		return Response.success(users);
	}
	
	//签到
	@RequestMapping(value="/signin")
	public Response userSignIn(HttpSession session)
	{
		try {
			SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		       User user = (User) context.getAuthentication().getPrincipal();
		       int uid=user.getId();
			Signin signin=userService.findUidSignin(uid);

			if(StringUtils.isEmpty(signin))
			{		
				//没查询到就插入签到
				userService.insterSigninUser(uid);	
				return Response.success("签到成功");
			}
			//检查今天是否签到过了			
			if(userService.UpdateUserIntegral(uid))return Response.success("签到成功");		
			return Response.failure("今天已经签过了");
		} catch (Exception e) {
			logger.error("出错了", e);
			return Response.failure("未知错误");
		}
		
	}
	
	
	
	//修改用户头像
	@RequestMapping(value="/changeIcon", method = RequestMethod.POST)
	public Response changeIcon(@RequestParam("files") MultipartFile files,HttpSession session)
	{
		try {
			 
			   User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			   System.out.println("fileName---------->" + files.getOriginalFilename());  
			String newfilename=USER_ICON + new Date().getTime() + files.getOriginalFilename();
			if(!files.isEmpty()){  
				int pre = (int) System.currentTimeMillis();  
				
				try {  
					//拿到输出流，同时重命名上传的文件  

					FileOutputStream os = new FileOutputStream(newfilename);  
					//拿到上传文件的输入流  
					FileInputStream in = (FileInputStream) files.getInputStream();  
					  
					//以写字节的方式写文件  
					int b = 0;  
					while((b=in.read()) != -1){  
						os.write(b);  
					}  
					os.flush();  
					os.close();  
					in.close();  
					int finaltime = (int) System.currentTimeMillis();  
					System.out.println(finaltime - pre);  
					  userService.changeUserIcon(user.getUsername(), newfilename);
					//ptservice.changeUserIcon(user.getId(), newfilename);
					
				} catch (Exception e) {  
					e.printStackTrace();  
					System.out.println("上传头像出错");  
					return Response.failure("上传头像出错");
				}  
		
			
			}
			return Response.success();
		} catch (Exception e) {
			return Response.failure();
		}
	
	}
	
	
}
