package com.tarena.Tast;

import java.awt.Paint;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tarena.snake.CellRandom;
import com.tarena.snake.Food;

public class Cell_test extends JPanel{
	public static void main(String[] args) {
		JFrame frame  =new JFrame();
		frame.setSize(800,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		CellRandom cell = new CellRandom();
		frame.add(cell);
		frame.setVisible(true);
		cell.action();
	}
}
