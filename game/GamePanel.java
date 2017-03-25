package game;

import game.random.Award;
import game.random.Ball;
import game.random.Ball_JP;
import game.random.Food;
import game.random.Money;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import static java.awt.event.KeyEvent.*;

/**
 * ʵ�ּ���ͼƬ
 */
public class GamePanel extends JPanel {
	public static BufferedImage background; // ����ͼ
	public static BufferedImage body; // ��ɫ����
	public static BufferedImage down; // ��ɫ��ͷ(��)
	public static BufferedImage up; // ��ɫ��ͷ(��)
	public static BufferedImage left; // ��ɫ��ͷ(��)
	public static BufferedImage right; // ��ɫ��ͷ(��)
	public static BufferedImage r_body; // ��ɫ����
	public static BufferedImage r_down; // ��ɫ��ͷ(��)
	public static BufferedImage r_up; // ��ɫ��ͷ(��)
	public static BufferedImage r_left; // ��ɫ��ͷ(��)
	public static BufferedImage r_right; // ��ɫ��ͷ(��)
	public static BufferedImage snake_food;// ʳ��
	public static BufferedImage T;
	public static BufferedImage seatebackground;
	public static BufferedImage xuanzebiejing;

	static {
		try {
			background = ImageIO.read(GamePanel.class
					.getResource("/images/background1.png"));// ����ͼ
			body = ImageIO
					.read(GamePanel.class.getResource("/images/body.png"));// ��ɫ����
			down = ImageIO
					.read(GamePanel.class.getResource("/images/down.png"));// ��ɫ��ͷ(��)
			up = ImageIO.read(GamePanel.class.getResource("/images/up.png"));// ��ɫ��ͷ(��)
			left = ImageIO
					.read(GamePanel.class.getResource("/images/left.png"));// ��ɫ��ͷ(��)
			right = ImageIO.read(GamePanel.class
					.getResource("/images/right.png"));// ��ɫ��ͷ(��)
			r_body = ImageIO.read(GamePanel.class
					.getResource("/images/r_body.png")); // ��ɫ����
			r_down = ImageIO.read(GamePanel.class
					.getResource("/images/r_down.png"));// ��ɫ��ͷ(��)
			r_up = ImageIO
					.read(GamePanel.class.getResource("/images/r_up.png"));// ��ɫ��ͷ(��)
			r_left = ImageIO.read(GamePanel.class
					.getResource("/images/r_left.png"));// ��ɫ��ͷ(��)
			r_right = ImageIO.read(GamePanel.class
					.getResource("/images/r_right.png"));// ��ɫ��ͷ(��)
			snake_food = ImageIO.read(GamePanel.class
					.getResource("/images/snake_food.png"));// ʳ��
			seatebackground = ImageIO.read(GamePanel.class
					.getResource("/images/snake_food.png"));// ʳ��
			T = ImageIO.read(GamePanel.class.getResource("/images/T.png"));// ���
			seatebackground = ImageIO.read(GamePanel.class
					.getResource("/images/seatebackground.png"));
			xuanzebiejing = ImageIO.read(GamePanel.class
					.getResource("/images/��ɫ1.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static final int backgroundwidth = background.getWidth();
	public static final int backgroundhight = background.getHeight();
	public static final int CELL_SIZE = (background.getWidth() / 30);// ����ÿһ�����ӵĳ���
	public static final int ROW = 30;// ��
	public static final int COL = 30;// ��
	public static int score;// ����
	public static int tim;// ��ʱ
	public static int life = 3;
	private Snake snake1;// ��1
	private ArrayList<Food> foods;// ʳ��
	private ArrayList<Money> moneys;// Ǯ��
	private ArrayList<FoodObject> foodObjects;
	private Ball_JP Balla;
	private Money money;// Ǯ
	public static boolean doMove = true;
	private FoodObject foodObject;
	Random random = new Random();
	public static final int READY = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;
	public static final int ACTIVE = 0;
	public static final int DEAD = 1;
	public static final int REMOVE = 2;
	private boolean flag = false;
	public static int status;//��Ϸ����״̬


	/**
	 * �������췽�� ��ʼ������
	 */
	public GamePanel() {
		snake1 = new Snake();// һ����
		Balla = new Ball_JP();// һЩ���
		foodObjects = new ArrayList<FoodObject>();
		status = RUNNING;
		nextOne();
		Timer();


		System.out.println(Balla);
	}

	/**
	 * ����ʳ�﷽��
	 */
	public void nextOne() {
		int num;
		num = random.nextInt(10);
		if (num < 2) {
			foodObjects.add(new Food());
		} else {
			foodObjects.add(new Money());
		}
	}

	private Runnable r1 ;//��������߳�
	private Runnable r2 ;//��ʱ�߳�
	private Runnable r3 ;//�������ػ��߳�
	private Runnable r4 ;//�������߳�
	private ExecutorService threadPool ;
	// ��ʱ��
	public void Timer() {
		r1 = ()-> {
			while (status == RUNNING) {
				moveStep();// �������ƶ�
				action();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println("ֹͣ�߳�1");
		};

		r2 = ()->{
			while(status == RUNNING){
				tim++;
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
			}
			System.out.println("ֹͣ�߳�2");
		};

		r3 = ()->{
			while (status == RUNNING){
				repaint();
				ballaMove();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("ֹͣ�߳�3");
		};

		r4 = ()->{
			do {
				System.out.println("�����߳�״̬");
			}while(status == PAUSE);
			if (status == RUNNING) {
				Restart();
				threadPool.execute(r1);
				threadPool.execute(r2);
				threadPool.execute(r3);
			}
		};
		threadPool = Executors.newFixedThreadPool(3);
		threadPool.execute(r1);
		threadPool.execute(r2);
		threadPool.execute(r3);
	}

	/**
	 * С����˶�
	 */
	public void ballaMove(){
		for (int i = 1; i < Balla.balls.size(); i++) { // �������˶�һ��
			Ball b = Balla.balls.get(i);
			b.move();
		}
	}
	/**
	 * �ߵ��ƶ�
	 */
	public void moveStep() {
		snake1.move();
	}

	/**
	 * ͳһ���ȵĶ���
	 */
	public void action() {
		KeyListen();// �����ƶ�
		addFood();
		Fuck();
		
	}

	/**
	 * ������
	 */
	public void KeyListen() {
		KeyAdapter k = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int a = e.getKeyCode();
				switch (a) {
				case VK_W:
					snake1.moveUp();
					break;
				case VK_S:
					snake1.moveDown();
					break;
				case VK_A:
					snake1.moveLeft();
					break;
				case VK_D:
					snake1.moveRight();
				}
			}
		};
		this.requestFocus();
		this.addKeyListener(k);
	}

	public void Fuck() {// ��ײ����
		Head head = (Head)snake1.length.get(0);
		for (int i = 0; i < foodObjects.size(); i++) {
			FoodObject foodObject = foodObjects.get(i);
			if (CrashObjects.crashBy(head, foodObject)) {
				foodObjects.remove(i);
				score += 10;
				addFood();
				addBody();
				if (foodObject instanceof Money) {
					Award award = (Award) foodObject;
					int type = award.getAward();
					switch (type) {
					case Award.LIFE:
						// MAX();
						break;
					case Award.RANDOMS:
						// score+=1000;
						break;
					case Award.MAX:
						// snake1.length.size()<=1;
						break;
					case Award.MIN:
						break;
					}
				}
			}
			
		}
		
		if (CrashObjects.qiang(head)) {
			life--;
			if(life > 0)
			Dead("������С����");
		}
		
		if (CrashObjects.State(snake1)) {
			life--;
			if(life > 0)
			Dead("ײ���Լ���");
		}
		if(life ==0)
		GameOverFrame();
		
	}

	public void Gameover() {
		snake1.length.clear();
		foodObjects.clear();
		snake1 = new Snake();
		nextOne();
		score = 0;
		tim = 0;
	}

	public void addFood() {
		if (flag) {
			nextOne();
			flag = false;
		}
	}

	public void addBody() {// �����ʳ��++
		// β������
		int tailX = snake1.length.get(snake1.length.size() - 1).x;
		int tailY = snake1.length.get(snake1.length.size() - 1).y;

		Direction tailDir = snake1.length.get( snake1.length.size() - 1).snakeDir;
		switch (tailDir) {
		case LIFT:
			snake1.length.add(new Body(tailX + 1, tailY, tailDir));
			break;
		case RIGHT:
			snake1.length.add(new Body(tailX - 1, tailY, tailDir));
			break;
		case UP:
			snake1.length.add(new Body(tailX, tailY + 1, tailDir));
			break;
		case DOWN:
			snake1.length.add(new Body(tailX, tailY - 1, tailDir));
			break;
		}
	}

	public void Restart(){
		snake1.length.clear();
		foodObjects.clear();
		snake1 = new Snake();
		nextOne();
		score = 0;
		tim = 0;
		life = 3;
	}
	
	public void GameOverFrame(){
		if (life == 0) {
			threadPool.execute(r4);
			status = PAUSE;
			new GameFrameson().GameOver(life);
		}
	}
	
	public void Dead(String s) {// ײǽ��ʾ
		
		Date date = new Date(0);
		SimpleDateFormat sb = new SimpleDateFormat();
		String Str2 = sb.format(date);
		String str = s + "\n" + "��Ϸ������" + life + "��";
		int i = JOptionPane.showConfirmDialog(null, Str2 + "\n" + str, "��Ϸ��ʾ",
				JOptionPane.YES_NO_OPTION);
		if (i == 0) {
			Gameover();
		} else {
			JOptionPane.showMessageDialog(null, "�˳���Ϸ", "����",
					JOptionPane.WARNING_MESSAGE);
			System.exit(i);
			return;
		}
		// System.exit(0);
	}

	public void paintDq(Graphics g) {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				int x = i * CELL_SIZE;
				int y = j * CELL_SIZE;
				g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
			}
			System.out.println();
		}
	}

	public void paintSnake(Graphics g) {
		for (Joint j : snake1.length) {
			g.drawImage(j.image, j.x * CELL_SIZE, j.y * CELL_SIZE, null);
		}
	}

	public void paintFoodObject(Graphics g) {
		for (FoodObject f : foodObjects) {
			g.drawImage(f.image, f.x * CELL_SIZE, f.y * CELL_SIZE, null);
		}
	}

	public void paintBall(Graphics g) {
		for (int i = 1; i < Balla.balls.size(); i++) {
			Ball b = Balla.balls.get(i);
			b.Draw(g);
		}
	}

	// ������ͼ,ʱ�䰴,����
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);// ������ͼ
		g.setColor(new Color(0xFFFFFF));
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.drawString("ʱ��:" + tim, 10, 20);// ��ʱ��
		g.drawString("����:" + score, 10, 50);// ������
		g.drawString("����" + life, 10, 80);
		paintSnake(g);// ��
		paintFoodObject(g);
		paintBall(g);
	}
}
