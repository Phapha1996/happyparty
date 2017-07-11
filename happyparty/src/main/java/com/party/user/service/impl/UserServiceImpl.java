package com.party.user.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.party.domain.Signin;
import com.party.domain.User;
import com.party.domain.UserRole;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
import com.party.tool.MD5;
import com.party.tool.javaMail;
import com.party.user.mapper.UserMapper;
import com.party.user.service.UserService;


@Service
public class UserServiceImpl implements UserService
{
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Value("${USER_ICON}")
	String USER_ICON;
	
	//增加用户
	@Transactional(propagation=Propagation.REQUIRED)
	public void addUser(User user)
	{
		
		user.setIcon(USER_ICON);
		user.setPassword(MD5.toMD5(user.getPassword()));
		user.setIntegral(0);
		user.setRegdate(new java.sql.Date(new java.util.Date().getTime()));
		user.setActivate(true);
		logger.info("增加用户中");
		int status=userMapper.insterUser(user);
		if(status==0)
			throw new ServiceException("增加用户失败！");
		logger.info("增加用户成功");
		
		UserRole userRole=new UserRole();
		userRole.setUser_id(user.getId());
		userRole.setRole_id(1);
		int statu=userMapper.insterUserRole(userRole);
		if(statu==0)
			throw new ServiceException("增加用户权限失败！");
		
	}
	
	//查询用户信息
	public User getUserMessage(int uid)
	{
		if(uid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		User user=userMapper.selectUserMessage(uid);
		if(user.equals(null))
			throw new ServiceException("获取用户信息失败！");
		return user;
	}
	
	
	//发送注册激活邮件
	public String sendRegisterEmail(String userName)
	{	
		if(StringUtils.isEmpty(userName))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		logger.info("发送注册邮件中");
		String state=javaMail.activation(userName);
		logger.info("发送注册邮件成功");
		return state;
	}
	
	//发送修改密码邮件
	public String sendPasswordEmail(String userName,String Password)
	{
		if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(Password))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		String state=javaMail.changePWD(userName, Password);
		return state;
	}
	//修改用户密码
	public void ChangeUserPassword(String userName,String Password)
	{
		if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(Password))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		Password=MD5.toMD5(Password);
		int status=userMapper.updateIdUserPassword(userName, Password);
		if(status==0)
			throw new ServiceException("修改用户密码失败！");

	}
	//激活用户
	public void activateUser(String userName)
	{
		if(StringUtils.isEmpty(userName))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		logger.info("用户激活中");
		int status=userMapper.updateUserActivate(userName);
		if(status==0)
			throw new ServiceException("激活用户失败！");
		logger.info("用户激活成功");
	}
	
	//检测用户名是否被注册
	public boolean DetectionUserName(String userName)
	{
		if(StringUtils.isEmpty(userName))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int status=userMapper.selectUserNameRegister(userName);
		if(status==0)return true;
		else 
			return false;
	}
	
	//修改用户姓名
	public void ChangeUserNickName(String userName,String nickName)
	{
		if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(nickName))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int status=userMapper.updateIdUserNickName(userName, nickName);
		if(status==0)
			throw new ServiceException("修改用户姓名失败");
	}
	
	
	//修改用户头像
	public void changeUserIcon(String userName,String icon)
	{
		if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(icon))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int status=userMapper.updateUserIcon(userName, icon);
		if(status==0)
			throw new ServiceException("修改用户头像失败");
	}
	
	
	//用户id查询签到
	public Signin findUidSignin(int uid)
	{
		if(uid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		Signin signin=userMapper.selectSignin(uid);
		return signin;
	}
	//增加用户第一次签到
	@Transactional(propagation=Propagation.REQUIRED)
	public void insterSigninUser(int uid)
	{
		if(uid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		
		 Signin signin=new Signin();
		 signin.setUser_id(uid);
		 signin.setSign_time(new java.sql.Date(new java.util.Date().getTime()));
		 signin.setCount(1);
		 int status=userMapper.insterSignin(signin);
		 logger.info("增加签到中");
		 if(status==0)
			throw new ServiceException("增加用户第一次签到失败");
		 logger.info("增加签到成功");
		 logger.info("更新签到积分中");
		int statu= userMapper.updateIntegral(uid,1);
		 logger.info("更新签到积分成功");
		if(statu==0)
			throw new ServiceException("更新用户积分失败");
		 
	}
	
	
	
	//更新签到时间和积分
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean  UpdateUserIntegral(int uid)
	{
		if(uid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		Integer integral=userMapper.selectIdUserIntegral(uid);
		 logger.info("用户积分为:"+integral);
		 Signin signin=userMapper.selectSignin(uid);
		 Date date=new java.sql.Date(new java.util.Date().getTime());
		 if(!this.isSameDate(signin.getSign_time(), date))
		 {
			 userMapper.updateTime(uid, date);
			 userMapper.updateIntegral(uid, integral+1);

			 return true;
		 }
		 return false;
	}
	
	
	//比较两个时间是否为同一天
	public  boolean isSameDate(Date date1, Date date2) {
	       Calendar cal1 = Calendar.getInstance();
	       cal1.setTime(date1);

	       Calendar cal2 = Calendar.getInstance();
	       cal2.setTime(date2);

	       boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
	               .get(Calendar.YEAR);
	       boolean isSameMonth = isSameYear
	               && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
	       boolean isSameDate = isSameMonth
	               && cal1.get(Calendar.DAY_OF_MONTH) == cal2
	                       .get(Calendar.DAY_OF_MONTH);

	       return isSameDate;
	   }
	
}
