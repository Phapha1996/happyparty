package com.party.security;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.party.dto.Response;
/**
 * 
 * @author Caizhf
 * @date 2017年6月5日下午3:42:55
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: SpringSecurity所需的控制器</p>
 *
 */
@Controller
@RequestMapping("/security")
public class SecurityController {
	
	/**
	 * 
	 * @param session
	 * @return 登录失败，返回异常信息
	 * 前端ajax需要跳转回登录页面
	 */
	@RequestMapping("/failure")
	@ResponseBody
	public Response loginFailue(HttpSession session){
		Exception e = (Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if(e.getMessage().equals("Bad credentials"))
			return Response.failure("密码错误");
		return Response.failure(e.getMessage());
	}
	
	/**
	 * session超时
	 * 前端Ajax处理需要跳转回登录页面
	 */
	@RequestMapping("/session_timeout")
	@ResponseBody
	public Response sessionTimeout(){
		return Response.failure("已经失去登录信息,请重新登录");
	}
	
	/**
	 * 登出成功
	 * @return
	 */
	@RequestMapping("/logout_success")
	public String logOutSuccess(){
		return "redirect:/login";
	}
	
}