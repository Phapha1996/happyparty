package com.party.tool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrdersNumUtils {
	//根据时间生成订单，组合：时间串+5位随机数
	public static String toOrdersNum(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String myDate = sdf.format(date);
		Random rd = new Random();
		int max = 99999;
		int min = 10000;
		int randomNum = rd.nextInt(max)%(max-min+1) + min;
		return myDate+randomNum;
	}
}
