package com.party.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.party.dto.Response;
/**
 * 
 * @author Caizhf
 * @date 2017年5月13日下午1:54:28
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 处理全局异常映射</p>
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	

	/**
	 * 业务层异常映射
	 */
	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Response defaultServiceExceptionHandler(HttpServletRequest request,ServiceException e) throws Exception{
		/*
		 如果要返回的是view
		ModelAndView mav = new ModelAndView();
		mav.setViewName("");
		 */
		logger.info("异常信息：",e);
		return Response.failure(e.getMessage());
	}

	  /**
	   * 操作数据库出现异常:名称重复，外键关联
	   */
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  @ExceptionHandler(DataIntegrityViolationException.class)
	  @ResponseBody
	  public Response handleException(DataIntegrityViolationException e) {
	    logger.error("操作数据库出现异常:", e);
	    return Response.failure("操作数据库出现异常：字段重复、有外键关联等");
	  }
	
	  /**
	   * 通用异常映射
	   */
		@ExceptionHandler(Exception.class)
		@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
		@ResponseBody
		public Response defaultExceptionHandler(HttpServletRequest request,Exception e) throws Exception{
			logger.error("异常信息：",e);
			return Response.failure(e.getMessage());
		}
	
	 /**
	   * 400 - Bad Request
	   *//*
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler(MissingServletRequestParameterException.class)
	  public AjaxResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
	    logger.error("缺少请求参数", e);
	    return new AjaxResult().failure("required_parameter_is_not_present");
	  }

	  *//**
	   * 400 - Bad Request
	   *//*
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler(HttpMessageNotReadableException.class)
	  public AjaxResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
	    logger.error("参数解析失败", e);
	    return new AjaxResult().failure("could_not_read_json");
	  }

	  *//**
	   * 400 - Bad Request
	   *//*
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  public AjaxResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
	    logger.error("参数验证失败", e);
	    BindingResult result = e.getBindingResult();
	    FieldError error = result.getFieldError();
	    String field = error.getField();
	    String code = error.getDefaultMessage();
	    String message = String.format("%s:%s", field, code);
	    return new AjaxResult().failure(message);
	  }

	  *//**
	   * 400 - Bad Request
	   *//*
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler(BindException.class)
	  public AjaxResult handleBindException(BindException e) {
	    logger.error("参数绑定失败", e);
	    BindingResult result = e.getBindingResult();
	    FieldError error = result.getFieldError();
	    String field = error.getField();
	    String code = error.getDefaultMessage();
	    String message = String.format("%s:%s", field, code);
	    return new AjaxResult().failure(message);
	  }

	  *//**
	   * 400 - Bad Request
	   *//*
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler(ConstraintViolationException.class)
	  public AjaxResult handleServiceException(ConstraintViolationException e) {
	    logger.error("参数验证失败", e);
	    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	    ConstraintViolation<?> violation = violations.iterator().next();
	    String message = violation.getMessage();
	    return new AjaxResult().failure("parameter:" + message);
	  }

	  *//**
	   * 400 - Bad Request
	   *//*
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler(ValidationException.class)
	  public AjaxResult handleValidationException(ValidationException e) {
	    logger.error("参数验证失败", e);
	    return new AjaxResult().failure("validation_exception");
	  }

	  *//**
	   * 405 - Method Not Allowed
	   *//*
	  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	  public AjaxResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
	    logger.error("不支持当前请求方法", e);
	    return new AjaxResult().failure("request_method_not_supported");
	  }

	  *//**
	   * 415 - Unsupported Media Type
	   *//*
	  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	  public AjaxResult handleHttpMediaTypeNotSupportedException(Exception e) {
	    logger.error("不支持当前媒体类型", e);
	    return new AjaxResult().failure("content_type_not_supported");
	  }

	  *//**
	   * 500 - Internal Server Error
	   *//*
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  @ExceptionHandler(ServiceException.class)
	  public AjaxResult handleServiceException(ServiceException e) {
	    logger.error("业务逻辑异常", e);
	    return new AjaxResult().failure("业务逻辑异常：" + e.getMessage());
	  }

	  *//**
	   * 500 - Internal Server Error
	   *//*
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  @ExceptionHandler(Exception.class)
	  public AjaxResult handleException(Exception e) {
	    logger.error("通用异常", e);
	    return new AjaxResult().failure("通用异常：" + e.getMessage());
	  }

	  */
}
