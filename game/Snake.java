package game;

import game.random.Food;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import sun.reflect.generics.tree.VoidDescriptor;
import sun.security.util.Length;

import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.rowset.internal.Row;

/**
 * 蛇类：
 *
 * @author soft01
 *
 */
public class Snake {
	//	private  int x;
//	private  int y;
	public int state;// 状态
	public static final int ACTIVE = 0;
	public static final int DEAD = 1;
	public static final int REMOVE = 2;

	public List<Joint> length;

	public Snake() {length = new ArrayList<>();}

	public Snake(int x, int y) {
		length = new ArrayList<>();
		length.add(new Head(x, y));
		length.add(new Body(x - 1, y));
		length.add(new Body(x - 2, y));
		length.add(new Body(x - 3, y));
		System.out.println(length);
	}

	/**
	 * 根据方向自动向前移动位置 LIFT,RIGHT,UP,DOWN
	 */
	public void move() {
		Direction temp = null;
		length.get(0).move();
		for (int i = length.size() - 1; i > 0; i--) {
			Joint j = length.get(i);
			j.move();
			temp = length.get(0).snakeDir;// 记录第一个蛇身的方向
			if (i == 1) {
				length.get(1).snakeDir = temp;
			}
			length.get(i).snakeDir = length.get(i - 1).snakeDir;// 把前一个蛇身的方向赋值后一个蛇身
		}
		GamePanel.doMove = true;
	}

	public void moveDown() {
		if (length.get(0).snakeDir != Direction.UP) {
			length.get(0).moveDown();
			GamePanel.doMove = false;
		}
	}

	public void moveRight() {
		if (length.get(0).snakeDir != Direction.LIFT) {
			length.get(0).moveRight();
			GamePanel.doMove = false;
		}
	}

	public void moveUp() {
		if (length.get(0).snakeDir != Direction.DOWN) {
			length.get(0).moveUp();
			GamePanel.doMove = false;
		}
	}

	public void moveLeft() {
		if (length.get(0).snakeDir != Direction.RIGHT) {
			length.get(0).moveLeft();
			GamePanel.doMove = false;
		}

	}

	@Override
	public String toString() {
		return "Snake{" +
				"length=" + length +
				'}';
	}

	/*
	 * public void subractLife(){//减命 life--; } public void addLife(){//增加命
	 * life++; } public int getLife(){//命 return life; }
	 */
	/** 增命 */
	/*
	 * public void addLife(){ //增命 life++; }
	 *//** 减命 */
	/*
	 * public void subtractLife(){ //减命 life--; }
	 *//** 获取命 */
	/*
	 * public int getLife(){ return life; } public boolean isActive(){//蛇是否是活动的
	 * return state==ACTIVE; }
	 */

	/*
	 * public void goDead(){ if(isActive()){ state=DEAD; } }
	 */
}
