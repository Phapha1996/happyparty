package com.party.topic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.Reply;
/**
 * 
 * @author Caizhf
 * @date 2017年6月10日下午10:40:20
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 回复</p>
 *
 */
public interface ReplyMapper {
	/**
	 * 增加
	 * @param r
	 * @return
	 */
	Integer save(Reply r);

	/**
	 * 根据帖子的id查回复的记录数
	 * @param tid
	 * @return
	 */
	Integer countListByTopicId(int tid);

	/**
	 * 根据帖子id分页查询所有帖子
	 * @param startindex
	 * @param pagesize
	 * @return
	 */
	List<Reply> listByTopicId(@Param("tid") int tid, @Param("startIndex") int startIndex, @Param("pageSize")int pageSize);
	
	/**
	 * 查看详情
	 * @param rid
	 * @return
	 */
	Reply findById(int rid);
}
