package game;


import game.mina.Server;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
	
public class ipwindow {
	static int jop=0;
	ipwindow(){
		JButton newgame=new JButton("创建游戏");
		JButton newgames =new JButton("加入游戏");
		JFrame jf = new JFrame();
		Component[] con={newgame,newgames};
	
		
		newgame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jop=1;
				jf.dispose();
			}
		});
		
		newgames.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jop=2;
				jf.dispose();
			}
		});
		
		
		JOptionPane.showOptionDialog(jf,"是否创建游戏","游戏模式",JOptionPane.YES_NO_OPTION
				,JOptionPane.QUESTION_MESSAGE,null,con ,con[0]);
		
		
		switch (jop) {
		case 1:
			new xuanze();
			new idwindow();
			GamePanel.serverHost = "localhost";
			GamePanel.isServer = true;
			break;
		case 2:
			String str = null;
			String not="(\\d{1,3}\\.){3}\\d{1,3}";
			str = JOptionPane.showInputDialog("请输入IP地址：例如172.168.1.1");
			while(!str.matches(not)){
				str = JOptionPane.showInputDialog("请输入IP地址：例如172.168.1.1");
				if(str.matches(not)){
					break;
			}
			}
			new xuanze();
			new idwindow();
			GamePanel.serverHost = str;
			GamePanel.isServer = false;
			break;
		}
		

			
	
	
	
		
	}
	public static void main(String[] args) {}
		

}

