package com.sgp.pcsv;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import com.sgp.pcsvutil.big2String;
import com.sgp.pcsvutil.codeString;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.*;
import com.univocity.parsers.common.record.*;
import com.univocity.parsers.conversions.*;
import com.univocity.parsers.csv.*;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;


public class PCSVRgh {
	/*读取报表导出文件（另存为csv后的0-16列,0为dk 3-16需要内容）为ArrayList1 
	 * &数据中心给出数据（INFO.csv，其中3列  2、22、27）  为ArrayList2
	 * ArrayList2 转hashmap   map2
	 * ArrayList1  与  map2匹配，
	 * 按顺序写入到txt文件（注意有无空额、引号等     身份证、证码是否有截取现象）
	 * 
	 */
	
	
//	public static Reader getReader(String relativePath) {
//		try {
//			return new InputStreamReader(PCSVRgh.class.getResourceAsStream(relativePath), "GBK");
//		} catch (UnsupportedEncodingException e) {
//			throw new IllegalStateException("Unable to read input", e);
//		}
//	}
	
	public static Reader getReader(String relativePath) throws FileNotFoundException {
		try {
			BufferedInputStream bin = new BufferedInputStream(  
			        new FileInputStream(relativePath));  
			return new InputStreamReader(bin, "GBK");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("Unable to read input", e);
		}
	}
	
	
// print out 2 console and file	
	public static void printAndValidate(Object[] headers, Collection<?> rows) throws IOException {

		if (headers != null) {
			System.out.println(Arrays.toString(headers));
			System.out.println("=======================");
		}

		int rowCount = 1;
		BufferedWriter bw=null;
//		FileWriter fw=null;
		boolean append=false;
		File outfile=new File("D:/javatest/PCSV/bin/out001.txt");
		//按默认编码写出
//		fw = new FileWriter(outfile,append);
//		bw = new BufferedWriter(fw,50000);
		FileOutputStream fw=null;
		fw = new FileOutputStream(outfile,append);
		//按指定编码写出
		bw = new BufferedWriter(new OutputStreamWriter(fw, "GBk"),50000);
		for (Object row : rows) {
			System.out.println((rowCount++) + " " + Arrays.toString((Object[]) row));	
			//打印第9列
//			String[] srow=(String[])row;
//			System.out.println((rowCount++) + " " + srow[8]);
			bw.write(Arrays.toString((Object[]) row)+"\r\n");
			bw.flush();			
			System.out.println("-----------------------");
		}
		bw.close();

	}
	
	/**
	 * Parses the example input file (/examples/example.csv) with a given setting.
	 * @param parserSettings settings used to parse the example.csv file
	 * @return a list with all parsed rows.
	 * @throws FileNotFoundException 
	 */
	private static List<String[]> parseWithSettings(CsvParserSettings parserSettings,String path01) throws FileNotFoundException {
		RowListProcessor rowProcessor = new RowListProcessor();

		parserSettings.setProcessor(rowProcessor);
		parserSettings.setHeaderExtractionEnabled(true);

		CsvParser parser = new CsvParser(parserSettings);
		parser.parse(getReader(path01));

		List<String[]> rows = rowProcessor.getRows();
		return rows;
	}
	//定义自己的数组tostring，参考arrays的toString方法
	public static String toString_sgp(Object[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.toString();
            b.append(",");
        }
    }



//	public static void main(String[] args) throws Exception {
		public void pcsv(String path01,String path02,String path03) throws Exception {
		CsvParserSettings settings = new CsvParserSettings();
		settings.getFormat().setLineSeparator("\r\n");

		// creates a CSV parser
		CsvParser parser = new CsvParser(settings);

		// ******parses all rows in one go.
		List<String[]> allRows1 = parser.parseAll(getReader(path01));
		//读取固定列
		settings.selectIndexes(2, 22, 27);
		List<String[]> allRows2 = parser.parseAll(getReader(path02));
    	//查看读取行数
		System.out.println(allRows1.size());
    	System.out.println(allRows2.size());
    	//allRows2转化为hashmap(插入另一个map中，索引和字符数组地址)
    	 Map<String, String[]> map = new HashMap<String, String[]>(); 
    	for (String[] s2: allRows2) {
//			System.out.println((rowCount++) + " " + Arrays.toString((Object[]) row));	
//    		map.put(s2[0].trim(), (String[])Arrays.copyOfRange(s2,0,3));  
    		map.put(s2[0].trim(), s2);  
    	}
    	
//    	//打印map 查看结果
//    	for(Entry<String, String[]> entry : map.entrySet()){  
//    	    System.out.println("Key="+entry.getKey()+",value="+entry.getValue());  
//    	}  

    	//匹配并输出
    	BufferedWriter bw=null;
		
		boolean append=false;
		String outfile_p=path03+"/121EXPORTTRADEINFO.txt";
		File outfile=new File(outfile_p);
		//默认编码写出
//		FileWriter fw=null;
//		fw = new FileWriter(outfile,append);
//		bw = new BufferedWriter(fw,50000);
		//指定编码写出
		FileOutputStream fw=null;
		fw = new FileOutputStream(outfile,append);
		bw = new BufferedWriter(new OutputStreamWriter(fw, "GBk"),50000);
		int i=0;
		int j=allRows1.size()-1;
		double k=0;
		String mes="";
		for (String[] s1: allRows1) {
			//跳过首行  匹配后为null
			if(i==0){i=i+1;}
			else 
			{
				if (i==j){
					//最后一行不打印回车换行
					String[] ss2=map.get(s1[0]); 
		    		System.out.println(map.get(s1[0]));
		    		String[] s6_16=Arrays.copyOfRange(s1, 6, 17);
		    		k=k+Double.parseDouble(s1[12]);
					bw.write(ss2[1].trim()+','+s1[4].trim()+','+ss2[2].trim()+','+toString_sgp(s6_16).trim());
					bw.flush();	
					System.out.println(i);
					String k_str=big2String.big2(k);
					System.out.println(k_str);
					mes="总计条数："+i+"   总计金额："+k_str;
					}
				else
				{
					//非最后一行均写出回车换行
				String[] ss2=map.get(s1[0]); 
				System.out.println(map.get(s1[0]));
				String[] s6_16=Arrays.copyOfRange(s1, 6, 17);
				k=k+Double.parseDouble(s1[12]);
				bw.write(ss2[1].trim()+','+s1[4].trim()+','+ss2[2].trim().replace("x", "X") +','+toString_sgp(s6_16).trim()+"\r\n");
				bw.flush();	
				i=i+1;
				}
			};
			
		}
		bw.close();
		
		
//		
		
		//验证写出文件的编码
		String code=null;
		code=codeString.getcode(outfile) ;
		System.out.println(code);
    	File orfile=new File(outfile_p);
    	code=codeString.getcode(orfile) ;
		System.out.println(code);
		
		//弹出窗口展示数据，并确认是否继续压缩
//		 JOptionPane.showMessageDialog(null,mes,"记录数及总额",JOptionPane.INFORMATION_MESSAGE);
		
		Object[] options = {"无误","有误"};

		//定制可供选择按钮

		int response=JOptionPane.showOptionDialog(null,mes,"记录数及总额",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		//选择无误后压缩
		if(response==0) {         

		JOptionPane.showMessageDialog(null,"您按下了无误","消   息",JOptionPane.INFORMATION_MESSAGE);//消息对话框      

		} else if(response==1) {           

		      JOptionPane.showMessageDialog(null,"您按下了有误","消息",JOptionPane.INFORMATION_MESSAGE);       

		}
    	
		//打印输出等操作函数
//		printAndValidate(null, allRows1);
		
		//******To read all rows of a CSV (iterator-style)
//		 parser.beginParsing(getReader("/hjxh.csv"));
//		String[] row;
//	    while ((row = parser.parseNext()) != null) {
//	        System.out.println( Arrays.toString(row));
//	    }
//	    // The resources are closed automatically when the end of the input is reached,
//	    // or when an error happens, but you can call stopParsing() at any time.
//	    // You only need to use this if you are not parsing the entire content.
//	    // But it doesn't hurt if you call it anyway.
//	    parser.stopParsing();
		
		
		//*****Column selection
//	    settings.selectFields("借款受理ID", "姓名(全)", "证件号码(全)");
//	    settings.selectIndexes(2, 22, 27);
//	    settings.selectIndexes(2);
//	    settings.setMaxCharsPerColumn(100);

	    // let's parse with these settings and print the parsed rows.
//	    List<String[]> parsedRows = parseWithSettings(settings);
//	    try {
//			printAndValidate(null, parsedRows);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		


	}

}
