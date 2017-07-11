package com.party.product.service;


import java.util.List;

import com.party.domain.MealCategory;
import com.party.domain.SetMeal;
/**
 * 
 * @author Caizhf
 * @date 2017年6月26日下午4:05:11
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 套餐</p>
 *
 */
public interface SetMealService {

	List<SetMeal> list(int pageNum, Integer categoryId, int pageSize);

	SetMeal findById(int smid);

	List<SetMeal> listByRandomNum(int num);

	List<SetMeal> listLikeByTitle(int pageNum, int pageSize, String key);

	MealCategory findCategoryById(int mcid);
	
	List<MealCategory> listCategory(int pageNum, int pageSize);
}
