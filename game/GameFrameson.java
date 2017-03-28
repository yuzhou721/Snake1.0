package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

public class GameFrameson {


	boolean yesno;//判断条件


	JLabel overed;//gameover文本
	JLabel Time;//时间文本
	JLabel Scouce;//得分文本
	JLabel snakelong ;//蛇身长文本
	JButton newGame;//重新开始按钮
	JButton Gameout;//结束游戏按钮
	JButton InterGame;//联网游戏按钮
	JButton Lase;//增加难度按钮
	public void GameOver (int life/*,int Time,int Scouce, int Long/* 命 分数 长度 时间*/){
		JFrame over = new JFrame();
		JPanel b =new JPanel();
		b.setBackground(Color.GRAY);

		Time = new JLabel("游戏时间:");//后面加上int Time
		Scouce = new JLabel("得分：");//后面加上int Scouse
		overed = new JLabel("GAMEOVER");
		snakelong = new JLabel("你的长度是"+""+"cm");//中间加上int Long
		newGame = new JButton("重新开始");
		InterGame = new JButton("联机对战");
		Gameout = new JButton("游戏结束");
		Lase = new JButton("修改难度");

		b.setLayout(null);
		over.add(b);


		//游戏结束
		Gameout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(1);
			}
		});

		//之后改为重新开始方法
		newGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GamePanel.status = GamePanel.RUNNING;
				over.toBack();
				//new GameFrame();
			}
		});

		InterGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("联机对战");
			}
		});
		Lase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				laseson();

			}
		});



		//设置字体大小
		Time.setBounds(150,80,200,50);
		Time.setFont(new Font("微软雅黑",5,20));
		Scouce.setBounds(150,110,200,50);
		Scouce.setFont(new Font("微软雅黑",5,20));
		snakelong.setBounds(150,140,200,50);
		snakelong.setFont(new Font("微软雅黑",5,20));
		overed.setBounds(150,30,200,80);
		overed.setFont(new Font("微软雅黑",10,30));

		//设置位置

		Gameout.setBounds(370,250,90,30);
		newGame.setBounds(30,250,90,30);
		InterGame.setBounds(150,250,90,30);
		Lase.setBounds(260,250,90,30);
		//设置窗口属性
		over.setVisible(true);
		over.setTitle("游戏结束");
		over.setAlwaysOnTop(true);
		over.setResizable(false);
		over.setBounds(460, 250, 500, 330);
		//添加东西到界面
		b.add(overed);
		b.add(newGame);
		b.add(Time);
		b.add(Scouce);
		b.add(snakelong);
		b.add(Gameout);
		b.add(InterGame);
		b.add(Lase);


	}
	JButton yes;//确定按钮
	JButton no;//取消按钮
	JRadioButtonMenuItem lase1;//单选按钮
	JRadioButtonMenuItem lase2;
	JRadioButtonMenuItem lase3;

	public int laseson(){

		JFrame Gamelaseson = new JFrame();
		JPanel Laseson = new JPanel();
		ButtonGroup Lase = new ButtonGroup();//单选组合
		yes = new JButton("确定");
		no = new JButton("取消");

		lase1 = new JRadioButtonMenuItem("难度1",false);
		lase2 = new JRadioButtonMenuItem("难度2",false);
		lase3 = new JRadioButtonMenuItem("难度3",false);


		Lase.add(lase1);
		Lase.add(lase2);
		Lase.add(lase3);

		Laseson.setBackground(Color.GRAY);
		Laseson.setLayout(null);

		Gamelaseson.add(Laseson);


		lase1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.head =0;
				System.out.println(GameFrame.head );

			}
		});

		lase2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.head  = 1;//速度调整状态值
				System.out.println(GameFrame.head );


			}
		});
		lase3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.head=2;//速度调整状态值
				System.out.println(GameFrame.head);

			}
		});




		/*Lase.setBounds(200,100,300,90)*/;
		lase1.setBounds(100,10,90,30);
		lase2.setBounds(100,50,90,30);
		lase3.setBounds(100,90,90,30);
		yes.setBounds(40,130,90,30);
		no.setBounds(180,130,90,30);

		Gamelaseson.setVisible(true);
		Gamelaseson.setTitle("难度设置");
		Gamelaseson.setAlwaysOnTop(true);
		Gamelaseson.setResizable(false);
		Gamelaseson.setBounds(550, 300, 300, 200);


		Laseson.add(lase1);
		Laseson.add(lase2);
		Laseson.add(lase3);
		Laseson.add(yes);
		Laseson.add(no);
		//	yes.setFocusPainted(false);//焦点设置失败

		//yes.setContentAreaFilled(false);//透明
		yes.setOpaque(false);
		yes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				yesno = true;
				if(yesno){
					System.out.println("确定");
					Gamelaseson.toBack();


				}
			}
		});
		no.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				yesno = false;
				if(!yesno){
					System.out.println("取消");
					Gamelaseson.toBack();

				}

			}
		});
		return GameFrame.head ;
	}



}
