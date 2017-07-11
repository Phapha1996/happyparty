package com.party.decoration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.party.domain.Decoration;
import com.party.domain.DecorationProduct;
import com.party.domain.Theme;

@Mapper
public interface DecorationMapper 
{
	//分页查询所有主题
	@Select("select t.thid,t.theme_name,i.img_url from theme t,img i where i.product_type='主题' and t.thid=i.product_id ORDER BY sequence DESC limit #{start},#{size}")
	@ResultMap("com.party.decoration.mapper.DecorationMapper.ThemeBaseMapped")
	List<Theme> selectAllTheme(@Param("start")Integer start,@Param("size")Integer size); 
	
	//查询主题下的场地布置
	@ResultMap("com.party.decoration.mapper.DecorationMapper.DecorationBaseMapped")
	List<Decoration> selectThemeDecoration(@Param("thid")Integer thid,@Param("start")Integer start,@Param("size")Integer size); 
	
	//按id查询场地布置详情
	@ResultMap("com.party.decoration.mapper.DecorationMapper.DecorationBaseMapped")
	Decoration selectByIdDecoration(@Param("did")Integer did); 
	
	//查询主题下的所有商品
	@ResultMap("com.party.decoration.mapper.DecorationMapper.DecorationProductBaseMapped")
	List<DecorationProduct> selectIdDecProduct(@Param("did")Integer did,@Param("start")Integer start,@Param("size")Integer size); 

	
	//随机返回几条场地布置信息
	@ResultMap("com.party.decoration.mapper.DecorationMapper.DecorationBaseMapped")
	List<Decoration> selectRandDecoration(@Param("thid")Integer thid,@Param("size")Integer size);

	DecorationProduct findByProductId(int dpid);
	
	
	int countListDecorationLikeByTitle(@Param("key")String key);

	/**
	 * 根据title模糊查询所有的布置
	 * 
	 * @param key
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<Decoration> listDecorationLikeByTitle(@Param("key") String key, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);
}
