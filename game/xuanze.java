package game;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class xuanze {
	static int a =0;
	static int snak=0;//ѡ���ɫ
	JLabel xzbj;
	JButton snake1;
	JButton snake2;
	JButton snake3;
	JButton snake4;
	JButton snake5;
	JButton snake6;
	JButton snake7;
	JButton snake8;
	xuanze(){
		JFrame jf = new JFrame("ѡ���ɫ");
		
		JPanel jp = new JPanel();
		ImageIcon snake1head = new ImageIcon(GamePanel.xuanzebiejing);
		jf.setBounds(250,50,snake1head.getIconWidth(), snake1head.getIconHeight());
		jp.setLayout(null);
		
		xzbj =new JLabel(snake1head);
		snake1 = new JButton();
		snake2 = new JButton();
		snake3 = new JButton();
		snake4 = new JButton();
		snake5 = new JButton();
		snake6 = new JButton();
		snake7 = new JButton();
		snake8 = new JButton();
		
		
		
		xzbj.setBounds(0,0,snake1head.getIconWidth(), snake1head.getIconHeight());
		snake1.setBounds(57,38,149,265);
		snake2.setBounds(296,38,149,265);
		snake3.setBounds(543,38,149,265);
		snake4.setBounds(788,38,149,265);
		snake5.setBounds(54,389,149,265);
		snake6.setBounds(296,389,149,265);
		snake7.setBounds(543,389,149,265);
		snake8.setBounds(788,389,149,265);
		
		snake1.setFocusPainted(false);
		snake2.setFocusPainted(false);
		snake3.setFocusPainted(false);
		snake4.setFocusPainted(false);
		snake5.setFocusPainted(false);
		snake6.setFocusPainted(false);
		snake7.setFocusPainted(false);
		snake8.setFocusPainted(false);
		
		snake1.setContentAreaFilled(false);
		snake2.setContentAreaFilled(false);
		snake3.setContentAreaFilled(false);
		snake4.setContentAreaFilled(false);
		snake5.setContentAreaFilled(false);
		snake6.setContentAreaFilled(false);
		snake7.setContentAreaFilled(false);
		snake8.setContentAreaFilled(false);
		
		
		

		
		snake1.addMouseListener(new MouseAdapter() {//ѡ���ɫ1
			public void mouseClicked(MouseEvent e){
				snak=1;
				new GameFrame();
				jf.dispose();
		}
		});
		snake2.addMouseListener(new MouseAdapter() {//ѡ���ɫ2
			public void mouseClicked(MouseEvent e){
				snak=2;
				new GameFrame();
				jf.dispose();
			}
		});//252
		snake3.addMouseListener(new MouseAdapter() {//ѡ���ɫ3
			public void mouseClicked(MouseEvent e){
				snak=3;
				new GameFrame();
				jf.dispose();
			}
		});
		snake4.addMouseListener(new MouseAdapter() {//ѡ���ɫ4
			public void mouseClicked(MouseEvent e){
				snak=4;
				new GameFrame();
				jf.dispose();
			}
		});
		snake5.addMouseListener(new MouseAdapter() {//ѡ���ɫ4
			public void mouseClicked(MouseEvent e){
				snak=5;
				new GameFrame();
				jf.dispose();
			}
		});
		snake6.addMouseListener(new MouseAdapter() {//ѡ���ɫ4
			public void mouseClicked(MouseEvent e){
				snak=6;
				new GameFrame();
				jf.dispose();
			}
		});
		snake7.addMouseListener(new MouseAdapter() {//ѡ���ɫ4
			public void mouseClicked(MouseEvent e){
				snak=7;
				new GameFrame();
				jf.dispose();
			}
		});
		snake8.addMouseListener(new MouseAdapter() {//ѡ���ɫ4
			public void mouseClicked(MouseEvent e){
				snak=8;
				new GameFrame();
				jf.dispose();
			}
		});
		
		jf.add(jp);
		jf.add(xzbj);//����ͼƬ��Ǳ���
		xzbj.add(snake1);//���Ӱ�ť
		xzbj.add(snake2);//���Ӱ�ť
		xzbj.add(snake3);//���Ӱ�ť
		xzbj.add(snake4);//���Ӱ�ť
		xzbj.add(snake5);//���Ӱ�ť
		xzbj.add(snake6);//���Ӱ�ť
		xzbj.add(snake7);//���Ӱ�ť
		xzbj.add(snake8);//���Ӱ�ť
	
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	



	


}