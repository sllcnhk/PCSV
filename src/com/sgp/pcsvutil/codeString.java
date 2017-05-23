package com.sgp.pcsvutil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class codeString {
    public static String getcode(File fileName) throws Exception{  
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
