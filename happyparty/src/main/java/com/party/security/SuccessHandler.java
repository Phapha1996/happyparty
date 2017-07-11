package com.party.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.party.dto.Response;
/**
 * 
 * @author Caizhf
 * @date 2017年6月5日下午2:48:33
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: json/传统web请求处理</p>
 *
 */
@Component
public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String requestType = request.getHeader("x-requested-with");
		
		//如果缓存request为空，说明就是只访问登录页面
		if (savedRequest == null) {
			 //如果requestType不为空，就是ajax请求
		    if (!StringUtils.isEmpty(requestType)) {
		        response.setStatus(HttpServletResponse.SC_OK);
		        response.getWriter().print(JSON.toJSONString(Response.success()));
		        response.getWriter().close();
		    } else {
		    	//如果为空，就是传统请求
		    	setDefaultTargetUrl("/index");
//		        setAlwaysUseDefaultTargetUrl(true);
		        super.onAuthenticationSuccess(request, response, authentication);
		    }
		    return ;
		} 
		
		clearAuthenticationAttributes(request);
		
		//缓存下来的url
		String targetUrl = savedRequest.getRedirectUrl();
		
		logger.info("Redirecting to DefaultSavedRequest Url: " + targetUrl);
		if (!StringUtils.isEmpty(requestType)) {
	        response.setStatus(HttpServletResponse.SC_OK);
	        response.getWriter().print(JSON.toJSONString(Response.success(targetUrl)));
	        response.getWriter().close();
	    } else {
	    	getRedirectStrategy().sendRedirect(request, response, targetUrl);
	    }
		
	}
	
	
	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}
	
	public RequestCache getRequestCache() {  
	       return requestCache;  
	}  
}
