package com.party.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.party.domain.MealCategory;
import com.party.domain.SetMeal;
import com.party.dto.Page;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
import com.party.product.mapper.MealCategoryMapper;
import com.party.product.mapper.SetMealMapper;
import com.party.product.service.SetMealService;
/**
 * 
 * @author Caizhf
 * @date 2017年6月26日下午4:13:20
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 聚会套餐</p>
 *
 */
@Service
public class SetMealServiceImpl implements SetMealService{
	
	@Autowired
	private SetMealMapper mapper;
	@Autowired
	private MealCategoryMapper categoryMapper;
	
	@Override
	@Transactional(readOnly=true)
	public List<SetMeal> list(int pageNum, Integer categoryId, int pageSize) {
		int totleRecord = mapper.countList(categoryId);
		Page page = new Page(totleRecord,pageNum,pageSize);
		List<SetMeal> list = mapper.list(categoryId,page.getStartindex(),pageSize);
		return list;
	}

	@Override
	public SetMeal findById(int smid) {
		SetMeal setMeal = mapper.findById(smid);
		return setMeal;
	}

	@Override
	public List<SetMeal> listByRandomNum(int num) {
		if(num==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		List<SetMeal> list = mapper.listByRandomNum(num);
		return list;
	}

	@Override
	public List<SetMeal> listLikeByTitle(int pageNum, int pageSize, String key) {
		if(pageNum==0||pageSize==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int totleRecord = mapper.countListLikeByTitle(key);
		Page page = new Page(totleRecord,pageNum,pageSize) ;
		List<SetMeal> list = mapper.listLikeByTitle(page.getStartindex(),pageSize,key);
		return list;
	}

	@Override
	public MealCategory findCategoryById(int mcid) {
		if(mcid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		MealCategory mc = categoryMapper.findById(mcid);
		return mc;
	}

	@Override
	public List<MealCategory> listCategory(int pageNum, int pageSize) {
		int totleRecord = categoryMapper.countList();
		Page page = new Page(totleRecord,pageNum,pageSize);
		List<MealCategory> list = categoryMapper.list(page.getStartindex(),pageSize);
		return list;
	}

}
