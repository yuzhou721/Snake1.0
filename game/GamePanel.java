package game;

import game.mina.*;
import game.random.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
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
			T = ImageIO.read(GamePanel.class.getResource("/images/T.png"));// 金币
			seatebackground = ImageIO.read(GamePanel.class
					.getResource("/images/seatebackground.png"));
			xuanzebiejing = ImageIO.read(GamePanel.class
					.getResource("/images/juese.png"));
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
	private Ball_JP Balla;
	public static boolean doMove = true;
	//	public static final int READY = 0;//准备状态
	public static final int RUNNING = 1;//运行状态
	//	public static final int PAUSE = 2;//暂停状态
	public static final int GAME_OVER = 3;
	//	public static final int ACTIVE = 0;
	public static final int DEAD = 2;//死亡状态
	//	public static final int REMOVE = 2;
	public static int status;// 客户端运行状态
	public static long id;//从服务器上分配的ID
	public static boolean isServer;
	private Server server;
	/**
	 * 存储所有的蛇
	 */
	public static Map<Long,Snake> snakes = null;

	/**
	 * 存储所有食物
	 */
	public static Set<FoodObject> foodObjects;

	/**
	 * 用来通信的客户端
	 */
	private Client client;

	/**
	 * 名字
	 */
	private static String name;

	/**
	 * 自己的蛇
	 */
	public static Snake snake;// 蛇1

	/**
	 * 服务器IP地址
	 */
	public static String serverHost;


	//get set方法 ---------------
	public static Map<Long, Snake> getSnakes() {
		return snakes;
	}

	public static void setSnakes(Map<Long, Snake> snakes) {
		GamePanel.snakes = snakes;
	}


//-----------------------------

	/**
	 * 画布构造方法 初始化数据
	 */
	public GamePanel() {
		snake = new Snake(snakeCreateRange(),snakeCreateRange());// 一条蛇
		Balla = new Ball_JP();// 一些随机
		foodObjects = new HashSet<FoodObject>();
		snakes = new HashMap<>();
		status = RUNNING;
		if (isServer){
			server = Server.getInstance();
			server.start();
		}
		name = "1";
		snakes = new HashMap<>();
		client = new Client(name,serverHost,9999);
		snake = new Snake(snakeCreateRange(),snakeCreateRange());
		client.start();
		Timer();
	}



	private Runnable r1 ;//动作检测线程
	private Runnable r2 ;//计时线程
	private Runnable r3 ;//背景和重画线程
	private Runnable r4 ;//触发器线程
	private ExecutorService threadPool ;//线程池
	// 计时器
	public void Timer() {

		r1 = ()-> {
			while (status == RUNNING) {
				action();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			threadPool.execute(r4);
			System.out.println("停止线程1");
		};

		r2 = ()->{
			while(true){
				tim++;
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}

			}
		};

		r3 = ()->{
			while (true){
				repaint();
				ballaMove();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		r4 = ()->{
			while(status == DEAD || status == GAME_OVER){

				System.out.println("线程监听");
				System.out.println("运行状态是"+status);
			}
			if (status == RUNNING){
				Restart();
			}
			threadPool.execute(r1);
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
		snake.move();
	}

	/**
	 * 统一调度的动作
	 */
	public void action() {
		KeyListen();// 方向移动
		crash();//启动碰撞监听
		moveStep();//蛇的移动
		sendSnake();//移动状态发送给服务器

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
						snake.moveUp();
						break;
					case VK_S:
						snake.moveDown();
						break;
					case VK_A:
						snake.moveLeft();
						break;
					case VK_D:
						snake.moveRight();
				}
			}
		};
		this.requestFocus();
		this.addKeyListener(k);
	}


	/**
	 * 碰撞监听
	 */
	public void crash() {
		Head head = (Head) snake.length.get(0);
		/*
		for (int i = 0; i < foodObjects.size(); i++) {
			FoodObject foodObject = foodObjects.get(i);
			if (CrashObjects.SnakeBang(head, foodObject)) {
				foodObjects.remove(i);
				score += 10;
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
						// snake.length.size()<=1;
						break;
					case Award.MIN:
						break;
					}
				}
			}

		}
		*/

		snakeCrashByOther(head);
		snakeCrashWall(head);
		snakeEat(head);
	}

	/**
	 * 头和其他蛇的头和身体的碰撞判定
	 * @param head 本机蛇头
	 */
	private void snakeCrashByOther(Head head){
		for (long pid
				:snakes.keySet()){
			Snake allSnake = snakes.get(pid);
			for (Joint joint
					:allSnake.length) {
				if (CrashObjects.SnakeBang(head,joint) && pid != id){
					decLife("撞到了");
				}
			}
		}
	}

	private void snakeEat(Head head){
		int i = 0;
		for (FoodObject food:
				foodObjects){
			if (CrashObjects.SnakeBang(head,food)){
				System.out.println("eat food");
				addBody();
				ClientUtil.sendSnakeData(id,snake, SnakeData.OPERATION_REL_SNAKE);
				ClientUtil.sendFoodObject(i,food, FoodObjectData.OPERATION_DEL_FOOD);
			}
		}
	}

	/**
	 * 蛇撞到墙
	 * @param head 本机蛇头
	 */
	private void snakeCrashWall(Head head){
		if (CrashObjects.qiang(head)) {
			decLife("哈哈，小垃圾");
//            status = DEAD;
		}
	}

	/**
	 * 游戏结束
	 */
	public void gameOver() {
		status = GAME_OVER;
		gameOverFrame();
	}


	/**
	 * 显示游戏结束窗口
	 */
	public void gameOverFrame(){
		if (life == 0) {
//            threadPool.execute(r4);
//            status = GAME_OVER;
			new GameFrameson().GameOver(life);
		}
	}


	/**
	 * 增加身体长度方法
	 */
	public void addBody() {// 身体吃食物++
		// 尾部坐标
		int tailX = snake.length.get(snake.length.size() - 1).getX;
		int tailY = snake.length.get(snake.length.size() - 1).y;
		Direction tailDir = snake.length.get( snake.length.size() - 1).snakeDir;
		switch (tailDir) {
			case LIFT:
				snake.length.add(new Body(tailX + 1, tailY, tailDir));
				break;
			case RIGHT:
				snake.length.add(new Body(tailX - 1, tailY, tailDir));
				break;
			case UP:
				snake.length.add(new Body(tailX, tailY + 1, tailDir));
				break;
			case DOWN:
				snake.length.add(new Body(tailX, tailY - 1, tailDir));
				break;
		}
//		System.out.println("add body");
	}

	/**
	 * 重新开始方法
	 */
	public void Restart(){
		snake = new Snake(snakeCreateRange(),snakeCreateRange());
		score = 0;
		tim = 0;
		life = 3;
		status = RUNNING;
	}

	/**
	 * 减血+弹窗方法
	 *
	 */
	public void decLife(String s) {// 撞墙提示
		life--;
		if (life > 0) {
			notice(s);
		}
		if (life == 0 ){
			gameOver();
		}
	}

	/**
	 * 减命后弹窗
	 * @param s 给玩家说的话
	 */
	private void notice(String s){
		status = DEAD;
		ClientUtil.sendSnakeData(id,snake,SnakeData.OPERATION_DEL_SNAKE);
		Date date = new Date(0);
		SimpleDateFormat sb = new SimpleDateFormat();
		String Str2 = sb.format(date);
		String str = s + "\n" + "游戏继续？" + life + "命";
		int i = JOptionPane.showConfirmDialog(null, Str2 + "\n" + str, "游戏提示",
				JOptionPane.YES_NO_OPTION);
		if (i == 0) {
			rebirth();

		} else {
			JOptionPane.showMessageDialog(null, "退出游戏", "标题",
					JOptionPane.WARNING_MESSAGE);
			System.exit(i);
			return;
		}
	}

	/**
	 * 撞死后重生方法
	 */
	public void rebirth(){
		snake = new Snake(snakeCreateRange(),snakeCreateRange());
		status = RUNNING;
	}

	/**
	 * 发送蛇给服务器
	 */
	public void sendSnake(){
		System.out.println("客户端发送的snake:"+ snake);
		if (status == RUNNING) {
			ClientUtil.sendSnake(snake);
//           Server.sendSnakes();
		}
//		if (status == DEAD){
//        	ClientUtil.sendSnakeData(id,snake,SnakeData.OPERATION_DEL_SNAKE);
//		}
	}

	public int snakeCreateRange(){
		return (int)((Math.random()*26)+4);
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
		for (Snake snake :
				snakes.values()) {
			for (Joint j : snake.length) {
				g.drawImage(j.image, j.getX * CELL_SIZE, j.y * CELL_SIZE, null);
			}
		}
	}

	public void paintFoodObject(Graphics g) {
		for (FoodObject f : foodObjects) {
			g.drawImage(f.getImage(), f.getX() * CELL_SIZE, f.getY() * CELL_SIZE, null);
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
