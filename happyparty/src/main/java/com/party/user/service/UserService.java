package com.party.user.service;

import java.util.Date;

import com.party.domain.Signin;
import com.party.domain.User;

public interface UserService 
{
	//增加用户
	public void addUser(User user);
	//查询用户信息
	public User getUserMessage(int uid);
	//发送注册激活邮件
	public String sendRegisterEmail(String userName);
	//发送修改密码邮件
	public String sendPasswordEmail(String userName,String Password);
	
	//修改用户密码
	public void ChangeUserPassword(String userName,String Password);
	//修改用户姓名
	public void ChangeUserNickName(String userName,String nickName);
	//修改用户头像
	public void changeUserIcon(String userName,String icon);
	
	//激活用户
	public void activateUser(String userName);
	
	//检测用户名是否被注册
	public boolean DetectionUserName(String userName);
	
	//用户id查询签到
	public Signin findUidSignin(int uid);
	//增加用户第一次签到
	public void insterSigninUser(int uid);
	
	//比较两个时间是否为同一天
	public boolean isSameDate(Date date1, Date date2);
	
	//更新签到时间和积分
	public boolean  UpdateUserIntegral(int uid);
	
	
	
	
}
