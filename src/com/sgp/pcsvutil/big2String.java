package com.sgp.pcsvutil;

import java.math.BigDecimal;

public class big2String {
	
	 public static String big2(double d) {
	        BigDecimal d1 = new BigDecimal(Double.toString(d));
	        BigDecimal d2 = new BigDecimal(Integer.toString(1));
	        // 四舍五入,保留2位小数
	        return d1.divide(d2,2,BigDecimal.ROUND_HALF_UP).toString();
	    }

}
