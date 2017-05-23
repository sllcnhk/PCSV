package com.sgp.pcsvutil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test003 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date= new Date();//创建一个时间对象，获取到当前的时间
      	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");//设置时间显示格式
      	SimpleDateFormat sdfm = new SimpleDateFormat("MM");//设置时间显示格式
      	String mondir = sdf.format(date);//将当前时间格式化为需要的类型
      	int mondir2 = Integer.parseInt(sdfm.format(date))-1;//将当前时间格式化为需要的类型
      	System.out.println(mondir);//输出结果
      	System.out.println(mondir2);//输出结果
	}

}
