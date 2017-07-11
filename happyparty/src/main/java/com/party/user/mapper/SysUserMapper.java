package com.party.user.mapper;

import com.party.domain.User;
/**
 * 
 * @author Caizhf
 * @date 2017年6月10日下午6:15:04
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: SpringSecurity需要的数据访问Mapper</p>
 *
 */
public interface SysUserMapper {
	User findByUsername(String username);
}
