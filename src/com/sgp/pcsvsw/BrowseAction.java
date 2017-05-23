package com.sgp.pcsvsw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sgp.pcsv.PCSVRgh;
import com.sgp.pcsvutil.createfl;

public class BrowseAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

     	  
    	if(e.getSource().equals(Interface01.button_1)){
          	//创建相关文件夹目录
          	Date date= new Date();//创建一个时间对象，获取到当前的时间
         	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");//设置时间显示格式
          	SimpleDateFormat sdfm = new SimpleDateFormat("MM");//设置时间显示格式
          	String mondir = sdf.format(date);//将当前时间格式化为需要的类型
          	int mondir2 = Integer.parseInt(sdfm.format(date))-1;//将当前时间格式化为需要的类型   	
          	String cdir01="D:/sgpwork/2016阶段/互金协会数据报送1604/反馈/"+mondir+"-"+mondir2;//生成两个需要创建的目录String
          	String cdir02=cdir01+"/源数据";
          	File file01=createfl.createDir(cdir01);
          	File file02=createfl.createDir(cdir02);
          	
            JFileChooser fcDlg = new JFileChooser();
//            File file01=new File("D:/sgpwork/2016阶段/互金协会数据报送1604/反馈");
//            fcDlg.setCurrentDirectory(file01.getAbsoluteFile());
              fcDlg.setCurrentDirectory(file01.getAbsoluteFile());
              fcDlg.setDialogTitle("请选择需要出的路径");
              fcDlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
              int returnVal = fcDlg.showOpenDialog(null);
              if (returnVal == JFileChooser.APPROVE_OPTION) {
              	File file = fcDlg.getSelectedFile();
  				System.out.println(file.getAbsolutePath());
                  String filepath = fcDlg.getSelectedFile().getPath();
                  Interface01.outpath.setText(filepath);
          	
              }
              
     //打开文件夹位置         
              try {
				java.awt.Desktop.getDesktop().open(file02);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("打开输出文件夹目录失败");;
			}
    	}
    	
    	else if (e.getSource().equals(Interface01.btncsv)) {
            JFileChooser fcDlg = new JFileChooser();
            File file01=new File("D:/sgpwork/2016阶段/互金协会数据报送1604/反馈");
            fcDlg.setCurrentDirectory(file01.getAbsoluteFile());
            fcDlg.setDialogTitle("请选择待处理的文件");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "文本文件(*.csv)", "csv");
            fcDlg.setFileFilter(filter);
            int returnVal = fcDlg.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	File file = fcDlg.getSelectedFile();
				System.out.println(file.getAbsolutePath());
                String filepath = fcDlg.getSelectedFile().getPath();
                Interface01.jcfn.setText(filepath);
            }  
          
            }
    	  
    	  else  if(e.getSource().equals(Interface01.btncsv_1)){
          	JFileChooser fcDlg = new JFileChooser();
              File file01=new File("D:/sgpwork/2016阶段/互金协会数据报送1604/反馈");
              fcDlg.setCurrentDirectory(file01.getAbsoluteFile());
              fcDlg.setDialogTitle("请选择待处理的文件");
              FileNameExtensionFilter filter = new FileNameExtensionFilter(
                      "文本文件(*.csv)", "csv");
              fcDlg.setFileFilter(filter);
              int returnVal = fcDlg.showOpenDialog(null);
              if (returnVal == JFileChooser.APPROVE_OPTION) {
              	File file = fcDlg.getSelectedFile();
  				System.out.println(file.getAbsolutePath());
                  String filepath = fcDlg.getSelectedFile().getPath();
                  Interface01.mgfn.setText(filepath);
          	
          }
    }
 
     	 else  if(e.getSource().equals(Interface01.btntxtzip)){
     		 String jcfn_c=Interface01.jcfn.getText().toString().replace("\\", "/");
     		System.out.println(jcfn_c);
     		 String mgfn_c=Interface01.mgfn.getText().toString().replace("\\", "/");
     		System.out.println(mgfn_c);
     		 String outpath_c=Interface01.outpath.getText().toString().replace("\\", "/");
     		System.out.println(outpath_c);
     		 PCSVRgh proc=new PCSVRgh();
				try {
					proc.pcsv(jcfn_c,mgfn_c,outpath_c);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
   }
    	
    	 else  if(e.getSource().equals(Interface01.button)){
    		 String outpath_c=Interface01.outpath.getText().toString().replace("\\", "/");
      		System.out.println(outpath_c);
      		File file02=new File(outpath_c);
    		   //打开文件夹位置         
             try {
				java.awt.Desktop.getDesktop().open(file02);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("打开输出文件夹目录失败");;
			}
   }
    	
    	 else  if(e.getSource().equals(Interface01.btnNewButton)){
    		 String mes="功能暂未实现";
    		 JOptionPane.showMessageDialog(null,mes,"记录数及总额",JOptionPane.INFORMATION_MESSAGE);
			
   }
    	  
    }
}

