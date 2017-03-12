package game;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import chuangk.Tp;

/**
 * 界面 
 * @author soft01
 *
 */
public class chuangkou extends JFrame{
	private static  int number = 0;
	private static int head= 0;
	private static  int lase = 0;
	
	
	
	
	
	public static void main(String[] args) {
		chuangkou ck=new chuangkou();
		Tp p =new Tp();
		ck.add(p);
		ck.setVisible(true);
		
		ck.setResizable(false);
	}
	
	public chuangkou(){
		super("贪吃蛇");
		Tp p =new Tp();
		String path = "snake_logo_看图王.png";
		try{
			Image img = ImageIO.read(this.getClass().getResource(path));
			this.setIconImage(img);;		
		}catch(IOException e){
			e.printStackTrace();
		}
		setBounds(300,150,p.backgroundwidth-10,p.backgroundhight+35);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menyber = new JMenuBar();//创建菜单栏
		setJMenuBar(menyber);//将创建的菜单栏添加到窗口菜单栏
		JMenu menu =new JMenu("游戏设置");//创建菜单对象
		menyber.add(menu);//添加到菜单栏
		JMenu gu =new JMenu("关于");//创建菜单对象
		menyber.add(gu);//添加到菜单栏
		
		JMenuItem menultem =new JMenuItem("重新开始");//子菜单对象
		//添加监听
		menultem.addActionListener(new ActionListener(){

			@Override//重新开始
			public void actionPerformed(ActionEvent e) {
				JMenuItem menuItem = (JMenuItem)e.getSource();
				number = 1;
			}
			
		});
		menu.add(menultem);//孙子菜单添加
		
		JMenu menultem2 =new JMenu("难度设置");//子菜单对象1
		
		menu.add(menultem2);//孙子菜单添加
		
		JMenuItem menultem1 =new JMenuItem("游戏结束");//子菜单对象1
		menultem1.addActionListener(new ActionListener() {
			
			@Override//结束游戏
			public void actionPerformed(ActionEvent e) {
				
				JMenuItem menuItem = (JMenuItem)e.getSource();
				
						lase = 1;
			}
		});
		menu.add(menultem1);//孙子菜单添加
		
		JMenuItem menultem3 =new JMenuItem("制作团队");//子菜单对象1
		menultem3.addActionListener(new ItemListenter());
		gu.add(menultem3);//孙子菜单添加
		
		
		//创建曾孙子菜单调整难度
		JMenuItem suzMenyItem =new JMenuItem("难度1");
		JMenuItem suzMenyItem1 =new JMenuItem("难度2");
		JMenuItem suzMenyItem2 =new JMenuItem("难度3");
		
		//难度调整鼠标捕捉
		suzMenyItem.addActionListener(new ActionListener(){

			@Override//初始难度
			public void actionPerformed(ActionEvent e) {
				JMenuItem menuItem = (JMenuItem)e.getSource();
				head = 0;
			}
			
		});
		suzMenyItem1.addActionListener(new ActionListener() {
			
			@Override//设置难度
			public void actionPerformed(ActionEvent e) {
				JMenuItem menuItem = (JMenuItem)e.getSource();
				head = 1;
			}
		});
		suzMenyItem2.addActionListener(new ActionListener() {
			
			@Override//设置难度
			public void actionPerformed(ActionEvent e) {
				JMenuItem menuItem = (JMenuItem)e.getSource();
				head = 2;				
			}
		});
		//难度调整添加到子菜单
		
		menultem2.add(suzMenyItem);
		menultem2.add(suzMenyItem1);
		menultem2.add(suzMenyItem2);
	
		
		
	}

	private  class ItemListenter implements ActionListener{
		
		JLabel I_code;//标记
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//鼠标捕捉
			JMenuItem menuItem = (JMenuItem)e.getSource();
			JFrame a= new JFrame();
			JPanel b =new JPanel();
			b.setBackground(Color.pink);
			a.add(b);
			I_code=new JLabel("制作人：vip组全员");
			a.setVisible(true);
			a.setBounds(300,200,200,60);
			I_code.setBounds(0, 0, 30, 30);
			b.add(I_code);
			a.setResizable(false);
			
		}
		
	}
}


