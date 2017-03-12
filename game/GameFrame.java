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

/**
 * ���� 
 * @author soft01
 *
 */
public class GameFrame extends JFrame{
	private static  int number = 0;
	private static int head= 0;
	private static  int lase = 0;


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
//		GameFrame frame = new GameFrame();
		GamePanel panel =new GamePanel();
		this.add(panel);
		String path = "/images/snake_logo_��ͼ��.png";
		try{
			Image img = ImageIO.read(this.getClass().getResource(path));
			this.setIconImage(img);
		}catch(IOException e){
			e.printStackTrace();
		}
		setBounds(300,150,panel.backgroundwidth-10,panel.backgroundhight+35);
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
				number = 1;
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
				
						lase = 1;
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
		//�Ѷȵ�����ӵ��Ӳ˵�
		
		menultem2.add(suzMenyItem);
		menultem2.add(suzMenyItem1);
		menultem2.add(suzMenyItem2);
	
		this.setVisible(true);
		this.setResizable(false);

		panel.Timer();

	}

	private  class ItemListenter implements ActionListener{
		
		JLabel I_code;//���
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//��겶׽
			JMenuItem menuItem = (JMenuItem)e.getSource();
			JFrame a= new JFrame();
			JPanel b =new JPanel();
			b.setBackground(Color.pink);
			a.add(b);
			I_code=new JLabel("�����ˣ�vip��ȫԱ");
			a.setVisible(true);
			a.setBounds(300,200,200,60);
			I_code.setBounds(0, 0, 30, 30);
			b.add(I_code);
			a.setResizable(false);
			
		}
		
	}
}


