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
	
	
	boolean yesno;//�ж�����

	
	JLabel overed;//gameover�ı�
	JLabel Time;//ʱ���ı�
	JLabel Scouce;//�÷��ı�
	JLabel snakelong ;//�����ı�
	JButton newGame;//���¿�ʼ��ť
	JButton Gameout;//������Ϸ��ť
	JButton InterGame;//������Ϸ��ť
	JButton Lase;//�����ѶȰ�ť
	public void GameOver (int life/*,int Time,int Scouce, int Long/* �� ���� ���� ʱ��*/){
			JFrame over = new JFrame();
			JPanel b =new JPanel();
			b.setBackground(Color.GRAY);
			
			Time = new JLabel("��Ϸʱ��:");//�������int Time
			Scouce = new JLabel("�÷֣�");//�������int Scouse
			overed = new JLabel("GAMEOVER");
			snakelong = new JLabel("��ĳ�����"+""+"cm");//�м����int Long
			newGame = new JButton("���¿�ʼ");
			InterGame = new JButton("������ս");
			Gameout = new JButton("��Ϸ����");
			Lase = new JButton("�޸��Ѷ�");

			b.setLayout(null);
			over.add(b);
			
			
			//��Ϸ����
			Gameout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				  System.exit(1);
				}
			});
			
			//֮���Ϊ���¿�ʼ����
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
					System.out.println("������ս");
				}
			});
			Lase.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					laseson();
					
				}
			});
			
			
			
			//���������С
			Time.setBounds(150,80,200,50);
			Time.setFont(new Font("΢���ź�",5,20));
			Scouce.setBounds(150,110,200,50);
			Scouce.setFont(new Font("΢���ź�",5,20));
			snakelong.setBounds(150,140,200,50);
			snakelong.setFont(new Font("΢���ź�",5,20));
			overed.setBounds(150,30,200,80);
			overed.setFont(new Font("΢���ź�",10,30));
			
			//����λ��
			
			Gameout.setBounds(370,250,90,30);
			newGame.setBounds(30,250,90,30);
			InterGame.setBounds(150,250,90,30);
			Lase.setBounds(260,250,90,30);
			//���ô�������
			over.setVisible(true);
			over.setTitle("��Ϸ����");
			over.setAlwaysOnTop(true);
			over.setResizable(false);
			over.setBounds(460, 250, 500, 330);
			//��Ӷ���������
			b.add(overed);
			b.add(newGame);
			b.add(Time);
			b.add(Scouce);
			b.add(snakelong);
			b.add(Gameout);
			b.add(InterGame);
			b.add(Lase);

		
	}
	JButton yes;//ȷ����ť
	JButton no;//ȡ����ť
	JRadioButtonMenuItem lase1;//��ѡ��ť
	JRadioButtonMenuItem lase2;
	JRadioButtonMenuItem lase3;
	
	public int laseson(){
		
		JFrame Gamelaseson = new JFrame();
		JPanel Laseson = new JPanel();
		ButtonGroup Lase = new ButtonGroup();//��ѡ���
		yes = new JButton("ȷ��");
		no = new JButton("ȡ��");
		
		lase1 = new JRadioButtonMenuItem("�Ѷ�1",false);
		lase2 = new JRadioButtonMenuItem("�Ѷ�2",false);
		lase3 = new JRadioButtonMenuItem("�Ѷ�3",false);
		
		
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
				GameFrame.head  = 1;//�ٶȵ���״ֵ̬
				System.out.println(GameFrame.head );
			

			}
		});
		lase3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.head=2;//�ٶȵ���״ֵ̬
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
		Gamelaseson.setTitle("�Ѷ�����");
		Gamelaseson.setAlwaysOnTop(true);
		Gamelaseson.setResizable(false);
		Gamelaseson.setBounds(550, 300, 300, 200);
		
		
		Laseson.add(lase1);
		Laseson.add(lase2);
		Laseson.add(lase3);
		Laseson.add(yes);
		Laseson.add(no);
	//	yes.setFocusPainted(false);//��������ʧ��
		
		//yes.setContentAreaFilled(false);//͸��
		yes.setOpaque(false);
		yes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				yesno = true;
				if(yesno){
					System.out.println("ȷ��");
					Gamelaseson.toBack();
					
					
				}
			}
		});
		no.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				yesno = false;
				if(!yesno){
					System.out.println("ȡ��");
					Gamelaseson.toBack();
					
				}
				
			}
		});
		return GameFrame.head ;
	}
	
	
	
}
