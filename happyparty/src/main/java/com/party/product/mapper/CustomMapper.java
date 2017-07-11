package com.party.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.party.domain.Custom;

@Mapper
public interface CustomMapper 
{
	//添加定制服务
	@Insert("INSERT INTO Custom(cuid,cu_type,cu_area,cu_time,cu_people_number,cu_user,cu_phone,cu_remark,cu_budget,cu_team) VALUES(#{cuid},#{cu_type},#{cu_area},#{cu_time},#{cu_people_number},#{cu_user},#{cu_phone},#{cu_remark},#{cu_budget},#{cu_team})")
	int insterCustom(Custom custom);
	
	
	//定制id查询定制服务
	@Select("SELECT * FROM Custom WHERE cuid= #{cuid}")
	Custom selectIdCustom(int cuid);
	
	
	//定制id删除定制服务
	@Delete("DELETE FROM Custom WHERE cuid=#{cuid}")
	int deleteCustoms(int cuid);
	
	
	//查询所有定制服务 倒序
	@Select("SELECT * FROM Custom  c ORDER BY c.cuid DESC LIMIT #{start},#{size}")
	List<Custom> selectAllCustom(@Param("start")int start,@Param("size")int size);
	
	//按手机号查询所有定制服务
	@Select("SELECT * FROM Custom c WHERE c.cu_phone=#{phone} ORDER BY c.cuid DESC LIMIT #{start},#{size}")
	List<Custom> selectPhoneCustom(@Param("phone")String phone,@Param("start")int start,@Param("size")int size);
}
