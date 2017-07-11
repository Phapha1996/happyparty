package com.party.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.party.domain.Collect;
import com.party.domain.Serve;

@Mapper
public interface CollectMapper 
{
	//增加收藏信息
	@Insert("INSERT INTO Collect(coid,product_type,product_id,user_id) VALUES(#{coid},#{product_type},#{product_id},#{user_id})")
	int insterCollect(Collect collect);
	
	//查询商品是否已被收藏
	@Select("select count(coid) from Collect where product_type=#{type} and product_id=#{pid} and user_id=#{uid}")
	int selectProductIsRegister(@Param("type")String type,@Param("pid")int pid,@Param("uid")int uid);
	
	//按id删除收藏信息
	@Delete("DELETE FROM Collect WHERE coid=#{coid}")
	int deleteIdCollect(int coid);
	
	//收藏里查询场地信息
	List<Collect> findByIdCollectSite(@Param("uid")int uid,@Param("start")int start,@Param("size")int size);
	
	//查询二级服务类
	List<Collect> findByTypeServe(@Param("uid")Integer uid,@Param("type")String type,@Param("start")Integer start,@Param("size")Integer size);

	//按收藏id查询二级服务
	Collect findByIdServe(@Param("coid")Integer coid);
	
	//按收藏id查询场地信息
	Collect findByIdSite(@Param("coid")Integer coid);
	
	//查询套餐
	List<Collect> findByTypeMeal(@Param("uid")Integer uid,@Param("type")String type,@Param("start")Integer start,@Param("size")Integer size);

	//按收藏id查询套餐
	Collect findByIdMeal(@Param("coid")Integer coid);
	

}
