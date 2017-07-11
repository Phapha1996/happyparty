package com.party.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author Caizhf
 * @date 2017年5月20日下午3:37:32
 * @version v.0.1
 * @title SpringMVC配置
 *        <p>
 *        Description: 当用户访问login时跳转到login.html页面。
 *        </p>
 * 
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("/index");
		registry.addViewController("/index").setViewName("/index");
		registry.addViewController("/topic").setViewName("/topic/index");
		registry.addViewController("/order").setViewName("/order");
		registry.addViewController("/topic/detail").setViewName("/topic/detail");
		registry.addViewController("/topic/reply").setViewName("/topic/reply");
		registry.addViewController("/topic/publish").setViewName("/topic/publish");
		registry.addViewController("/login").setViewName("/user/login");
		registry.addViewController("/user/register").setViewName("/user/register");
		registry.addViewController("/user/collect").setViewName("/user/collect");
		registry.addViewController("/user").setViewName("/user/index");
		registry.addViewController("/upload").setViewName("upload");
		registry.addViewController("/user/setting").setViewName("/user/setting");
		registry.addViewController("/service").setViewName("/service/index");
		registry.addViewController("/service/detail").setViewName("/service/detail");
		registry.addViewController("/catering").setViewName("/catering/index");
		registry.addViewController("/catering/detail").setViewName("/catering/detail");
		registry.addViewController("/site").setViewName("/site/index");
		registry.addViewController("/site/detail").setViewName("/site/detail");
		registry.addViewController("/site/discuss").setViewName("/site/discuss");
		registry.addViewController("/siteLayout").setViewName("/siteLayout/index");
		registry.addViewController("/siteLayout/detail").setViewName("/siteLayout/detail");
		registry.addViewController("/siteLayout/good_detail").setViewName("/siteLayout/good_detail");
		registry.addViewController("/siteLayout/list").setViewName("/siteLayout/list");
		registry.addViewController("/custom").setViewName("/custom");
		registry.addViewController("/user/order").setViewName("/user/order");
		registry.addViewController("/meal").setViewName("/meal/index");
		registry.addViewController("/meal/list").setViewName("/meal/list");
		registry.addViewController("/meal/detail").setViewName("/meal/detail");
		registry.addViewController("/discuss/publish").setViewName("/discuss/publish");
		registry.addViewController("/search").setViewName("/search");
	}
}