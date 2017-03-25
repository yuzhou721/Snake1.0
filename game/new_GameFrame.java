package game;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javafx.beans.property.SimpleDoubleProperty;

import javax.imageio.ImageIO;
import javax.swing.*;




public class new_GameFrame extends JFrame{

	
	JLabel label;//底层背景界面
	JLabel bel;//上层界面
	JButton start;//游戏开始
	JButton help;//帮助
	JButton settings;//设置
	JButton exit;//退出
	JButton test;//退出
	JOptionPane exits;
	public new_GameFrame(){
	super("贪吃蛇");
	String path = "/images/snake_logo_看图王.png";
	
	this.setBounds(330,170,GamePanel.seatebackground.getWidth(),GamePanel.seatebackground.getHeight());
	
	ImageIcon background = new ImageIcon(GamePanel.seatebackground);
	label = new JLabel(background);	
	bel = new JLabel();
	start = new JButton("开始");
	help = new JButton();
	settings = new JButton();
	exit = new JButton();
	test= new JButton("测试");
	exits =new JOptionPane();
	
	
	
	label.setBounds(0, 0,this.getWidth(),this.getHeight());
	bel.setBounds(0, 0,this.getWidth(),this.getHeight());
	start.setBounds(270,140,270,65);
	help.setBounds(270,220,270, 65);
	exit.setBounds(270,380,350, 65);
	test.setSize(90,30);

	start.setContentAreaFilled(false);
	start.setFocusPainted(false);
	start.setOpaque(false);
	help.setContentAreaFilled(false);
	help.setFocusPainted(false);
	settings.setBounds(270,300,270, 65);
	settings.setContentAreaFilled(false);
	settings.setFocusPainted(false);
	exit.setContentAreaFilled(false);
	exit.setFocusPainted(false);
	
	JPanel imagePanel = (JPanel)this.getContentPane();
	
	start.addMouseListener(new MouseAdapter() {
		@Override//开始按钮
		public void mouseClicked(MouseEvent e) {
				new xuanze();
				dispose();
				
		}
	});
	help.addMouseListener(new MouseAdapter() {
		@Override//帮助按钮
		public void mouseClicked(MouseEvent e) {
			
			System.out.println("help");
			
			
		}
	});
	settings.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
			System.out.println("settings");
		}
	});
	
	exit.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
			int a =exits.showOptionDialog(null, "是否退出", "退出？", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
			switch (a) {
			case 0:
				System.exit(1);	
				return;
			case 1:
				
				break;
			
			}
		}
	});
	
	
	imagePanel.setOpaque(true);

	this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
	try{
		Image img = ImageIO.read(this.getClass().getResource(path));
		this.setIconImage(img);
	}catch(IOException e){
		e.printStackTrace();
	}
	
	
	
	
	
	
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	add(label);
	add(bel);
	exits.add(test);
	bel.add(settings);
	bel.add(help);
	bel.add(start);
	bel.add(exit);
	
	setResizable(true);
	
	}
	











}
