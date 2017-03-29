package game;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class idwindow {

	private static int jop=0;
	idwindow(){

		String str = null;
		str = JOptionPane.showInputDialog("请输入昵称，如杀马特老大");
		while("".equals(str)){
			System.out.println("输入错误");
			str = JOptionPane.showInputDialog("请输入昵称，如杀马特老大");

		}
		GamePanel.name = str;
	}
	
		
}



