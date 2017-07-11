package com.party.home.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.party.domain.Play;
import com.party.domain.Recommend;

@Mapper
public interface HomeMapper 
{
	//增加推荐公告
	@Insert("INSERT INTO recommend(reid,title,link_url) VALUES(#{reid},#{title},#{link_url})")
	int insterRecommend(Recommend recommend);
	
	//删除推荐公告(id)
	@Delete("DELETE FROM recommend WHERE reid=#{reid}")
	int DeleteByIdRecommend(@Param("reid")Integer reid);
	
	//查询推荐公告（倒序）
	@Select("SELECT * FROM recommend ORDER BY reid DESC")
	List<Recommend> SelectByRecommend();
	
	//更新推荐公告
	@Update("UPDATE recommend SET title=#{title},link_url=#{url} WHERE reid=#{reid}")
	int updateByIdRecommend(@Param("title")String title,@Param("url")String url,@Param("reid")Integer reid);

	//增加轮播
	@Insert("INSERT INTO play(pid,img_url,link_url,ptype) VALUES(#{pid},#{img_url},#{link_url},#{ptype})")
	int insterPlay(Play play);
	
	//查询轮播（倒序）
	@Select("SELECT * FROM play WHERE ptype=#{type} ORDER BY pid DESC")
	List<Play> SelectByPlay(@Param("type")String type);
	
	//更新轮播
	@Update("UPDATE play SET link_url=#{url} WHERE pid=#{pid}")
	int updateByIdPlay(@Param("url")String url,@Param("pid")Integer pid);

	//删除轮播(id)
	@Delete("DELETE FROM play WHERE pid=#{pid}")
	int DeleteByIdPlay(@Param("pid")Integer pid);
	
	//查询轮播（倒序）
	@Select("SELECT * FROM play WHERE pid=#{pid}")
	Play SelectByIdPlay(@Param("pid")Integer pid);
}
