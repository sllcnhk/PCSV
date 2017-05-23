package com.sgp.pcsvsw;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Interface01 {

	private JFrame frame;
	public static JTextField jcfn;
	public static JTextField mgfn;
	public static JTextField outpath;
	public static JButton btncsv;
	public static JButton btncsv_1;
	public static JButton button_1;
	public static JButton btntxtzip;
	public static JButton button;
	public static JButton btnNewButton;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface01 window = new Interface01();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface01() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(10, 38, 501, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel  contentPane=new  JPanel();
		//把其它组件添加到Jpanel中;
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		button_1 = new JButton("准备并打开文件夹");
		button_1.addActionListener(new BrowseAction());
//		button_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		button_1.setToolTipText("（手动选择改变/默认路径存储）");
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(35, 10, 147, 30);
		contentPane.add(button_1);
		
		//输出文件位置
		outpath = new JTextField();
		outpath.setToolTipText("输出文件保存位置");
		outpath.setColumns(10);
		outpath.setBounds(223, 11, 236, 29);
		contentPane.add(outpath);
		
		
		JLabel lblNewLabel = new JLabel("请选择待待处理文件：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(35, 64, 305, 22);
		contentPane.add(lblNewLabel);
		
		
		btncsv = new JButton("选择基础csv文件");
		btncsv.addActionListener(new BrowseAction());
		btncsv.setBackground(Color.LIGHT_GRAY);
		btncsv.setBounds(35, 96, 147, 30);
//		btncsv.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});

		frame.getContentPane().add(btncsv);
		
		btncsv_1 = new JButton("选择敏感信息csv文件");
		btncsv_1.addActionListener(new BrowseAction());
		btncsv_1.setBackground(Color.LIGHT_GRAY);
		btncsv_1.setBounds(35, 136, 147, 30);
//		btncsv_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		frame.getContentPane().add(btncsv_1);
		
		
		jcfn = new JTextField();
		jcfn.setBounds(223, 97, 236, 29);
		frame.getContentPane().add(jcfn);
		jcfn.setColumns(10);
		jcfn.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				System.out.println(jcfn.getText().length());
			}
		});
		
	    btnNewButton = new JButton("确认对压缩文件加密");
	    btnNewButton.addActionListener(new BrowseAction());
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(35, 273, 178, 30);
		frame.getContentPane().add(btnNewButton);
		
		mgfn = new JTextField();
		mgfn.setColumns(10);
		mgfn.setBounds(223, 136, 236, 29);
		contentPane.add(mgfn);
		
		btntxtzip = new JButton("开始处理");
		btntxtzip.setBackground(Color.LIGHT_GRAY);
		btntxtzip.setToolTipText("（源文件读取匹配-输出为txt文件-压缩为zip文件）");
		btntxtzip.addActionListener(new BrowseAction());
//		btntxtzip.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		btntxtzip.setBounds(35, 193, 178, 30);
		contentPane.add(btntxtzip);
		
	    button = new JButton("打开输出文件文件夹");
		button.addActionListener(new BrowseAction());
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		button.setToolTipText("（源文件读取匹配-输出为txt文件-压缩为zip文件）");
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(35, 233, 178, 30);
		contentPane.add(button);

		
		
		jcfn.setEditable(false);// 设置源文件文本域不可手动修改
	    mgfn.setEditable(false);
	    outpath.setEditable(false);// 设置目标位置文本域不可手动修改
	}
	

}
