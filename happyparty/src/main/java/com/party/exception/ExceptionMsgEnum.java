package com.party.exception;
/**
 * 
 * @author Caizhf
 * @date 2017年6月11日下午3:08:39
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 异常常量</p>
 *
 */
public enum ExceptionMsgEnum {
	SERVER_ERROR_MSG("操作失败,服务器出现未知错误"),
	CLIENT_PARAM_ERROR_MSG("请不要传入无效参数或者空参数"),
	SERVER_DB_NOTEXIT("数据库中已经不存在这条记录了");
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private ExceptionMsgEnum(String msg) {
		this.msg = msg;
	}
}
