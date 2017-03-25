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
 * 实现加载图片
 */
public class GamePanel extends JPanel {
	public static BufferedImage background; // 背景图
	public static BufferedImage body; // 绿色蛇身
	public static BufferedImage down; // 绿色蛇头(下)
	public static BufferedImage up; // 绿色蛇头(上)
	public static BufferedImage left; // 绿色蛇头(左)
	public static BufferedImage right; // 绿色蛇头(右)
	public static BufferedImage r_body; // 红色蛇身
	public static BufferedImage r_down; // 红色蛇头(下)
	public static BufferedImage r_up; // 红色蛇头(上)
	public static BufferedImage r_left; // 红色蛇头(左)
	public static BufferedImage r_right; // 红色蛇头(右)
	public static BufferedImage snake_food;// 食物
	public static BufferedImage T;
	public static BufferedImage seatebackground;
	public static BufferedImage xuanzebiejing;

	static {
		try {
			background = ImageIO.read(GamePanel.class
					.getResource("/images/background1.png"));// 背景图
			body = ImageIO
					.read(GamePanel.class.getResource("/images/body.png"));// 绿色蛇身
			down = ImageIO
					.read(GamePanel.class.getResource("/images/down.png"));// 绿色蛇头(下)
			up = ImageIO.read(GamePanel.class.getResource("/images/up.png"));// 绿色蛇头(上)
			left = ImageIO
					.read(GamePanel.class.getResource("/images/left.png"));// 绿色蛇头(左)
			right = ImageIO.read(GamePanel.class
					.getResource("/images/right.png"));// 绿色蛇头(右)
			r_body = ImageIO.read(GamePanel.class
					.getResource("/images/r_body.png")); // 红色蛇身
			r_down = ImageIO.read(GamePanel.class
					.getResource("/images/r_down.png"));// 红色蛇头(下)
			r_up = ImageIO
					.read(GamePanel.class.getResource("/images/r_up.png"));// 红色蛇头(上)
			r_left = ImageIO.read(GamePanel.class
					.getResource("/images/r_left.png"));// 红色蛇头(左)
			r_right = ImageIO.read(GamePanel.class
					.getResource("/images/r_right.png"));// 红色蛇头(右)
			snake_food = ImageIO.read(GamePanel.class
					.getResource("/images/snake_food.png"));// 食物
			seatebackground = ImageIO.read(GamePanel.class
					.getResource("/images/snake_food.png"));// 食物
			T = ImageIO.read(GamePanel.class.getResource("/images/T.png"));// 金币
			seatebackground = ImageIO.read(GamePanel.class
					.getResource("/images/seatebackground.png"));
			xuanzebiejing = ImageIO.read(GamePanel.class
					.getResource("/images/角色1.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static final int backgroundwidth = background.getWidth();
	public static final int backgroundhight = background.getHeight();
	public static final int CELL_SIZE = (background.getWidth() / 30);// 代表每一个格子的长度
	public static final int ROW = 30;// 行
	public static final int COL = 30;// 列
	public static int score;// 分数
	public static int tim;// 计时
	public static int life = 3;
	private Snake snake1;// 蛇1
	private ArrayList<Food> foods;// 食物
	private ArrayList<Money> moneys;// 钱币
	private ArrayList<FoodObject> foodObjects;
	private Ball_JP Balla;
	private Money money;// 钱
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
	public static int status;//游戏运行状态


	/**
	 * 画布构造方法 初始化数据
	 */
	public GamePanel() {
		snake1 = new Snake();// 一条蛇
		Balla = new Ball_JP();// 一些随机
		foodObjects = new ArrayList<FoodObject>();
		status = RUNNING;
		nextOne();
		Timer();


		System.out.println(Balla);
	}

	/**
	 * 生成食物方法
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

	private Runnable r1 ;//动作检测线程
	private Runnable r2 ;//计时线程
	private Runnable r3 ;//背景和重画线程
	private Runnable r4 ;//触发器线程
	private ExecutorService threadPool ;
	// 计时器
	public void Timer() {
		r1 = ()-> {
			while (status == RUNNING) {
				moveStep();// 测试蛇移动
				action();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println("停止线程1");
		};

		r2 = ()->{
			while(status == RUNNING){
				tim++;
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
			}
			System.out.println("停止线程2");
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
			System.out.println("停止线程3");
		};

		r4 = ()->{
			do {
				System.out.println("监听线程状态");
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
	 * 小球的运动
	 */
	public void ballaMove(){
		for (int i = 1; i < Balla.balls.size(); i++) { // 所有球运动一次
			Ball b = Balla.balls.get(i);
			b.move();
		}
	}
	/**
	 * 蛇的移动
	 */
	public void moveStep() {
		snake1.move();
	}

	/**
	 * 统一调度的动作
	 */
	public void action() {
		KeyListen();// 方向移动
		addFood();
		Fuck();
		
	}

	/**
	 * 监听器
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

	public void Fuck() {// 碰撞监听
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
			Dead("哈哈，小垃圾");
		}
		
		if (CrashObjects.State(snake1)) {
			life--;
			if(life > 0)
			Dead("撞到自己了");
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

	public void addBody() {// 身体吃食物++
		// 尾部坐标
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
	
	public void Dead(String s) {// 撞墙提示
		
		Date date = new Date(0);
		SimpleDateFormat sb = new SimpleDateFormat();
		String Str2 = sb.format(date);
		String str = s + "\n" + "游戏继续？" + life + "命";
		int i = JOptionPane.showConfirmDialog(null, Str2 + "\n" + str, "游戏提示",
				JOptionPane.YES_NO_OPTION);
		if (i == 0) {
			Gameover();
		} else {
			JOptionPane.showMessageDialog(null, "退出游戏", "标题",
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

	// 画背景图,时间按,分数
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);// 画背景图
		g.setColor(new Color(0xFFFFFF));
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.drawString("时间:" + tim, 10, 20);// 画时间
		g.drawString("分数:" + score, 10, 50);// 画分数
		g.drawString("命：" + life, 10, 80);
		paintSnake(g);// 蛇
		paintFoodObject(g);
		paintBall(g);
	}
}
