package com.sgp.pcsv;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Filein2PrintCodeAndcontent {
	public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
    	 String fileName="D:/javatest/PCSV/bin/test.csv";
    	 File file = null;
		 try {
			file = new File(fileName);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 
		 String code = null;
			try {
				code = codeString(fileName);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		getcon(file,code);
		System.out.println(code);
	}
	
	
	
	//以字符流的方式读取文本，逐行显示，并返回
    public static String getcon (File infile, String incode){
    	File file=infile;
    	String code=incode;
    FileInputStream fInputStream = null;
	try {
		fInputStream = new FileInputStream(file);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
    StringBuffer sBuffer=new StringBuffer(50000);
  //code为上面方法里返回的编码方式  
  InputStreamReader inputStreamReader = null;
try {
	inputStreamReader = new InputStreamReader(fInputStream,code);
} catch (UnsupportedEncodingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}  
  BufferedReader inb = new BufferedReader(inputStreamReader,50000);  
    
  String strTmp = "";  
  //按行读取  
  try {
	while (( strTmp = inb.readLine()) != null) {  
	      sBuffer.append(strTmp + "\r\n");  
	      System.out.println(strTmp + "\r\n");
	  }
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}  
  return sBuffer.toString(); 
    }
    
	
    //获取编码方式
    public static String codeString(String fileName) throws Exception{  
        BufferedInputStream bin = new BufferedInputStream(  
        new FileInputStream(fileName));  
        int p = (bin.read() << 8) + bin.read();  
        String code = null;  
          
        switch (p) {  
            case 0xefbb:  
                code = "UTF-8";  
                break;  
            case 0xfffe:  
                code = "Unicode";  
                break;  
            case 0xfeff:  
                code = "UTF-16BE";  
                break;  
            default:  
                code = "GBK";  
        }  
          
        return code;  
    } 
	
}
