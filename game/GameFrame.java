package game;

import game.random.Ball_JP;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * ���� 
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
		super("̰����");
		  /*Ball_JP jp=new Ball_JP();  
	        jp.run_run();       //��ʼ���ж��߳�  
	        this.add(jp); */
//		GameFrame frame = new GameFrame();
		GamePanel panel =new GamePanel();
		this.add(panel);
//		GamePanel  jp1 = new GamePanel ();
//		jp.setOpaque(false);
//		this.add(jp1);
		
		String path = "/images/snake_logo_��ͼ��.png";
		try{
			Image img = ImageIO.read(this.getClass().getResource(path));
			this.setIconImage(img);
		}catch(IOException e){
			e.printStackTrace();
		}
		setBounds(300,0,panel.backgroundwidth-10,panel.backgroundhight+35);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menyber = new JMenuBar();//�����˵���
		setJMenuBar(menyber);//�������Ĳ˵�����ӵ����ڲ˵���
		JMenu menu =new JMenu("��Ϸ����");//�����˵�����
		menyber.add(menu);//��ӵ��˵���
		JMenu gu =new JMenu("����");//�����˵�����
		menyber.add(gu);//��ӵ��˵���
		
		JMenuItem menultem =new JMenuItem("���¿�ʼ");//�Ӳ˵�����
		//��Ӽ���
		menultem.addActionListener(new ActionListener(){

			@Override//���¿�ʼ
			public void actionPerformed(ActionEvent e) {
				JMenuItem menuItem = (JMenuItem)e.getSource();
				
				panel.Restart();
				
			}
			
		});
		menu.add(menultem);//���Ӳ˵����
		
		JMenu menultem2 =new JMenu("�Ѷ�����");//�Ӳ˵�����1
		
		menu.add(menultem2);//���Ӳ˵����
		
		JMenuItem menultem1 =new JMenuItem("��Ϸ����");//�Ӳ˵�����1
		menultem1.addActionListener(new ActionListener() {
			
			@Override//������Ϸ
			public void actionPerformed(ActionEvent e) {
				
				JMenuItem menuItem = (JMenuItem)e.getSource();
				 	System.exit(1);
						
			}
		});
		menu.add(menultem1);//���Ӳ˵����
		
		JMenuItem menultem3 =new JMenuItem("�����Ŷ�");//�Ӳ˵�����1
		menultem3.addActionListener(new ItemListenter());
		gu.add(menultem3);//���Ӳ˵����
		
		
		//���������Ӳ˵������Ѷ�
		JMenuItem suzMenyItem =new JMenuItem("�Ѷ�1");
		JMenuItem suzMenyItem1 =new JMenuItem("�Ѷ�2");
		JMenuItem suzMenyItem2 =new JMenuItem("�Ѷ�3");
		
		//�Ѷȵ�����겶׽
		suzMenyItem.addActionListener(new ActionListener(){

			@Override//��ʼ�Ѷ�
			public void actionPerformed(ActionEvent e) {
				JMenuItem menuItem = (JMenuItem)e.getSource();
				head = 0;
			}
			
		});
		suzMenyItem1.addActionListener(new ActionListener() {
			
			@Override//�����Ѷ�
			public void actionPerformed(ActionEvent e) {
				JMenuItem menuItem = (JMenuItem)e.getSource();
				head = 1;
			}
		});
		suzMenyItem2.addActionListener(new ActionListener() {
			
			@Override//�����Ѷ�
			public void actionPerformed(ActionEvent e) {
				JMenuItem menuItem = (JMenuItem)e.getSource();
				head = 2;				
			}
		});
		if(GamePanel.status == GamePanel.RUNNING){
			super.toBack();
		}
		
		//�Ѷȵ�����ӵ��Ӳ˵�
		
		menultem2.add(suzMenyItem);
		menultem2.add(suzMenyItem1);
		menultem2.add(suzMenyItem2);
            //������ӵ�����  
          
        this.setVisible(true);
		this.setVisible(true);
		this.setResizable(false);

		

		
	}

	private  class ItemListenter implements ActionListener{
		
		JLabel I_code;//���
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//��겶׽
//			JMenuItem menuItem = (JMenuItem)e.getSource();
//			JFrame a= new JFrame();
//			JPanel b =new JPanel();
//			
//			b.setBackground(Color.pink);
//			a.add(b);
//			I_code=new JLabel("�����ˣ�vip��ȫԱ");
//			a.setBounds(300,200,200,60);
//			
//			I_code.setBounds(0, 0, 30, 30);
//			a.setVisible(true);
//			b.add(I_code);
//			a.setResizable(false);		
			
			JOptionPane guanyu =new JOptionPane();
		
			int a =guanyu.showOptionDialog(null, "�Ƿ��˳�", "�˳���", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
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


