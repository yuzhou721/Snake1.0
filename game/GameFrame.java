package game;

import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * 界面
 * @author soft01
 *
 */
public class GameFrame extends JFrame{
	static  int number = 0;
	static int head= 0;
	static  int lase = 0;


	public void run(){
//        GameFrame ck=new GameFrame();
//        GamePanel p =new GamePanel();
//        ck.add(p);
//        ck.setVisible(true);
//
//        ck.setResizable(false);
	}

	public GameFrame(){
//			super("贪吃蛇");
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        if (GamePanel.isServer) {
			setTitle("贪吃蛇 服务器 IP地址："+ia.getHostAddress());
		}else {
			setTitle("贪吃蛇 客户端 服务器IP地址为"+GamePanel.serverHost);
		}
		  /*Balls jp=new Balls();
	        jp.run_run();       //开始运行多线程
	        this.add(jp); */
//		GameFrame frame = new GameFrame();
		GamePanel panel =new GamePanel();
		this.add(panel);
		Gamemusic.backgroundpaly();
//		GamePanel  jp1 = new GamePanel ();
//		jp.setOpaque(false);
//		this.add(jp1);

		String path = "/images/snake_logo_看图王.png";
		try{
			Image img = ImageIO.read(this.getClass().getResource(path));
			this.setIconImage(img);
		}catch(IOException e){
			e.printStackTrace();
		}
		setBounds(300,0,panel.backgroundwidth-10,panel.backgroundhight+35);
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

				panel.Restart();

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
				System.exit(1);

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
		if(GamePanel.status == GamePanel.RUNNING){
			super.toBack();
		}

		//难度调整添加到子菜单

		menultem2.add(suzMenyItem);
		menultem2.add(suzMenyItem1);
		menultem2.add(suzMenyItem2);
		//画布添加到窗体

		this.setVisible(true);
		this.setVisible(true);
		this.setResizable(false);




	}

	private  class ItemListenter implements ActionListener{

		JLabel I_code;//标记

		@Override
		public void actionPerformed(ActionEvent e) {
			//鼠标捕捉
//			JMenuItem menuItem = (JMenuItem)e.getSource();
//			JFrame a= new JFrame();
//			JPanel b =new JPanel();
//
//			b.setBackground(Color.pink);
//			a.add(b);
//			I_code=new JLabel("制作人：vip组全员");
//			a.setBounds(300,200,200,60);
//
//			I_code.setBounds(0, 0, 30, 30);
//			a.setVisible(true);
//			b.add(I_code);
//			a.setResizable(false);

			JOptionPane guanyu =new JOptionPane();

			int a =guanyu.showOptionDialog(null, "是否退出", "退出？", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
			switch (a) {
				case 0:
					System.exit(1);
					return;
				case 1:

					break;


			}

		}
	}
}


