package com.sgp.pcsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class StringWrite2file {
	//以字节流读入后以字符流写出
    public static void rawchar (File infile, String incode,File outfile2,boolean append ){
    	File jinfile=infile;
    	String jincode=incode;
    	File joutfile2=outfile2;
    	boolean jappend=append;
    	FileReader  fr=null;
    	FileInputStream  fis=null;
    	InputStreamReader isr=null;
    	BufferedReader br=null;
    	BufferedWriter bw=null;
    	FileWriter fw=null;
    	String str=null;
    	try {
			 fis = new FileInputStream(jinfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			isr=new InputStreamReader(fis,jincode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	br = new BufferedReader(isr);
    	
    	try {
			fw = new FileWriter(joutfile2,jappend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	FileOutputStream fopstream = null;
		try {
			fopstream = new FileOutputStream(joutfile2);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
			OutputStreamWriter opstreamw = new OutputStreamWriter(fopstream, jincode) ;
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	bw = new BufferedWriter(fw,100);
    	try {
			while ((str=br.readLine())!=null){
				bw.write(str+"\r\n");
				bw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally
    	{
    		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    		try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
		
			
		
    	
    }

}
