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
 * ���ࣺ
 * 
 * @author soft01
 *
 */
public class Snake {
	private static int x;
	private static int y;
	public int state;// ״̬
	public static final int ACTIVE = 0;
	public static final int DEAD = 1;
	public static final int REMOVE = 2;

	static Random random = new Random();
	public List<Joint> length;

	public Snake() {
		this((int) (Math.random() * 26 + 4), (int) (Math.random() * 26 + 4));
	}

	public Snake(int x, int y) {
		length = new ArrayList<>();
		length.add(new Head(x, y));
		length.add(new Body(x - 1, y));
		length.add(new Body(x - 2, y));
		length.add(new Body(x - 3, y));
		System.out.println(length);
	}

	/**
	 * ���ݷ����Զ���ǰ�ƶ�λ�� LIFT,RIGHT,UP,DOWN
	 */
	public void move() {
		Direction temp = null;
		length.get(0).move();
		for (int i = length.size() - 1; i > 0; i--) {
			Joint j = length.get(i);
			j.move();
			temp = length.get(0).snakeDir;// ��¼��һ������ķ���
			if (i == 1) {
				length.get(1).snakeDir = temp;
			}
			length.get(i).snakeDir = length.get(i - 1).snakeDir;// ��ǰһ������ķ���ֵ��һ������
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
	/*
	 * public void subractLife(){//���� life--; } public void addLife(){//������
	 * life++; } public int getLife(){//�� return life; }
	 */
	/** ���� */
	/*
	 * public void addLife(){ //���� life++; }
	 *//** ���� */
	/*
	 * public void subtractLife(){ //���� life--; }
	 *//** ��ȡ�� */
	/*
	 * public int getLife(){ return life; } public boolean isActive(){//���Ƿ��ǻ��
	 * return state==ACTIVE; }
	 */

	/*
	 * public void goDead(){ if(isActive()){ state=DEAD; } }
	 */
}
