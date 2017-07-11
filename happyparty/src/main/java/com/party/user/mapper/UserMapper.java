package com.party.user.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.party.domain.Signin;
import com.party.domain.User;
import com.party.domain.UserRole;
/**
 * 
 * @author Caizhf
 * @date 2017年6月10日下午2:48:19
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 测试例子</p>
 *
 */

@Mapper
public interface UserMapper {
	User findById(int id);
	
	//增加用户
	@Insert("INSERT INTO User(id,nickname,username,password,icon,integral,regdate,is_activate) VALUES(#{id},#{nickname},#{username},#{password},#{icon},#{integral},#{regdate},#{activate})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insterUser(User user);
	
	//增加用户角色
	@Insert("INSERT INTO user_role(urid,user_id,role_id) VALUES(#{urid},#{user_id},#{role_id})")
	int insterUserRole(UserRole userRole);
	
	//增加用户签到
	@Insert("INSERT INTO Signin(siid,sign_time,user_id,count) VALUES(#{siid},#{sign_time},#{user_id},#{count})")
	int insterSignin(Signin sigin);
	
	//查询用户名是否被注册
	@Select("select count(id) from User where username=#{userName}")
	int selectUserNameRegister(String userName);
	//查询用户信息
	@Select("SELECT nickname,username,icon,integral FROM User WHERE id = #{id}")
	User selectUserMessage(@Param("id") int id);
	
	//查询签到信息
	@Select("SELECT * FROM Signin WHERE user_id = #{uid}")
	@Results({
		@Result(property = "sign_time",  column = "sign_time", javaType = java.util.Date.class)
	})
	Signin selectSignin(@Param("uid") int uid);
	
	//查询用户积分
	@Select("SELECT integral FROM User WHERE id = #{id}")
	//@ResultMap("com.party.user.mapper.UserMapper.UserBaseMapped")
	int selectIdUserIntegral(@Param("id") int id);
	
	//激活用户
	@Update("UPDATE User SET is_activate=1 WHERE username=#{userName}")
	int updateUserActivate(String userName);
	
	//修改用户密码
	@Update("UPDATE User SET password=#{password} WHERE username=#{userName}")
	int updateIdUserPassword(@Param("userName") String userName,@Param("password")String password);
	//修改用户姓名
	@Update("UPDATE User SET nickname=#{nickName} WHERE username=#{userName}")
	int updateIdUserNickName(@Param("userName") String userName,@Param("nickName")String nickName);
	//修改用户头像
	@Update("UPDATE User SET icon=#{icon} WHERE username=#{userName}")
	int updateUserIcon(@Param("userName") String userName,@Param("icon")String icon);
	//更新用户积分
	@Update("UPDATE User SET integral=#{integral} WHERE id=#{uid}")
	int updateIntegral(@Param("uid")int uid,@Param("integral")int integral);
	//更新签到时间
	@Update("UPDATE Signin SET sign_time=#{time} WHERE user_id=#{uid}")
	@Results({
		@Result(property = "sign_time",  column = "sign_time", javaType = java.util.Date.class)
	})
	void updateTime(@Param("uid")int uid,@Param("time")Date time);
}
