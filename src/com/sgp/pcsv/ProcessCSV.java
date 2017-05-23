package com.sgp.pcsv;

import java.util.*;
import java.io.*;
import com.univocity.parsers.csv.*;

public class ProcessCSV {
	
   public static   void tostart() throws IOException{
	   String filePath01="D:/javatest/PCSV/hjxh.csv";
	   File file = new File(filePath01);  
       InputStream in = new FileInputStream(file);  
       InputStreamReader reader = new InputStreamReader(in, "GBK"); 
	   

		 CsvParserSettings settings = new CsvParserSettings();
		    // 文件中使用 '\n' 作为行分隔符
		    // 确保像MacOS和Windows这样的系统
		    // 也可以正确处理（MacOS使用'\r'；Windows使用'\r\n'）
		    settings.getFormat().setLineSeparator("\r\n");

		    // 创建CSV解析器
		    CsvParser parser = new CsvParser(settings);

		    // 一行语句处理所有行
		    List<String[]> allRows = parser.parseAll(reader);
		   
		    Iterator<String[]> it =allRows.iterator();
	        while(it.hasNext()){
	        	for(String i: it.next()){
	    		    System.out.print(i+',');
	    	        	}
	        	System.out.println();
	        }
	   
   }
    

	public static void main(String[] args) {
		try {
			tostart();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	
	}
	
}

