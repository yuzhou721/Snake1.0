package game;

import game.mina.*;
import game.random.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import static java.awt.event.KeyEvent.*;

/**
 * 实现加载图片
 */
public class GamePanel extends JPanel {
	public static final int UP = 1; // 上
	public static final int DOWN = 2; // 下
	public static final int RIGHT = 3; // 右
	public static final int LEFT = 4;//左


	public static Map<Integer, List<BufferedImage>> map = new HashMap<Integer,List<BufferedImage>>();

	static {
		try {
			//Map<String, List<BufferedImage>> map = new HashMap<String,List<BufferedImage>>();
			List<BufferedImage> listhat = new ArrayList<BufferedImage>();
			//帽子蛇
			listhat.add(ImageIO.read(GamePanel.class.getResource("/images/head1_up.png")));
			listhat.add(ImageIO.read(GamePanel.class.getResource("/images/head1_right.png")));
			listhat.add(ImageIO.read(GamePanel.class.getResource("/images/head1_down.png")));
			listhat.add(ImageIO.read(GamePanel.class.getResource("/images/head1_left.png")));
			listhat.add(ImageIO.read(GamePanel.class.getResource("/images/body1.png")));
			System.out.println(listhat);
			//青蛙蛇
			List<BufferedImage> listfrog = new ArrayList<BufferedImage>();
			listfrog.add(ImageIO.read(GamePanel.class.getResource("/images/head2_up.png")));
			listfrog.add(ImageIO.read(GamePanel.class.getResource("/images/head2_right.png")));
			listfrog.add(ImageIO.read(GamePanel.class.getResource("/images/head2_down.png")));
			listfrog.add(ImageIO.read(GamePanel.class.getResource("/images/head2_left.png")));
			listfrog.add(ImageIO.read(GamePanel.class.getResource("/images/body2.png")));
			System.out.println(listfrog);
			//蓝头蛇
			List<BufferedImage> listblue = new ArrayList<BufferedImage>();
			listblue.add(ImageIO.read(GamePanel.class.getResource("/images/head3_up.png")));
			listblue.add(ImageIO.read(GamePanel.class.getResource("/images/head3_right.png")));
			listblue.add(ImageIO.read(GamePanel.class.getResource("/images/head3_down.png")));
			listblue.add(ImageIO.read(GamePanel.class.getResource("/images/head3_left.png")));
			listblue.add(ImageIO.read(GamePanel.class.getResource("/images/body3.png")));
			System.out.println(listblue );
			//黑头蛇
			List<BufferedImage > listblack = new ArrayList<BufferedImage>();
			listblack.add(ImageIO.read(GamePanel.class.getResource("/images/head4_up.png")));
			listblack.add(ImageIO.read(GamePanel.class.getResource("/images/head4_right.png")));
			listblack.add(ImageIO.read(GamePanel.class.getResource("/images/head4_down.png")));
			listblack.add(ImageIO.read(GamePanel.class.getResource("/images/head4_left.png")));
			listblack.add(ImageIO.read(GamePanel.class.getResource("/images/body4.png")));
			System.out.println(listblack );
			//4眼蛇
			List<BufferedImage > list4eyes = new ArrayList<BufferedImage >();
			list4eyes.add(ImageIO.read(GamePanel.class.getResource("/images/head5_up.png")));
			list4eyes.add(ImageIO.read(GamePanel.class.getResource("/images/head5_right.png")));
			list4eyes.add(ImageIO.read(GamePanel.class.getResource("/images/head5_down.png")));
			list4eyes.add(ImageIO.read(GamePanel.class.getResource("/images/head5_left.png")));
			list4eyes.add(ImageIO.read(GamePanel.class.getResource("/images/body5.png")));
			System.out.println(list4eyes );
			//红色蛇
			List<BufferedImage> listred = new ArrayList<BufferedImage>();
			listred.add(ImageIO.read(GamePanel.class.getResource("/images/head6_up.png")));
			listred.add(ImageIO.read(GamePanel.class.getResource("/images/head6_right.png")));
			listred.add(ImageIO.read(GamePanel.class.getResource("/images/head6_down.png")));
			listred.add(ImageIO.read(GamePanel.class.getResource("/images/head6_left.png")));
			listred.add(ImageIO.read(GamePanel.class.getResource("/images/body6.png")));
			System.out.println(listred);
			//黄头蛇
			List<BufferedImage> listyellow = new ArrayList<BufferedImage>();
			listyellow.add(ImageIO.read(GamePanel.class.getResource("/images/head7_up.png")));
			listyellow.add(ImageIO.read(GamePanel.class.getResource("/images/head7_right.png")));
			listyellow.add(ImageIO.read(GamePanel.class.getResource("/images/head7_down.png")));
			listyellow.add(ImageIO.read(GamePanel.class.getResource("/images/head7_left.png")));
			listyellow.add(ImageIO.read(GamePanel.class.getResource("/images/body7.png")));
			System.out.println(listyellow);
			//娃娃蛇
			List<BufferedImage> listbaby = new ArrayList<BufferedImage>();
			listbaby.add(ImageIO.read(GamePanel.class.getResource("/images/head8_up.png")));
			listbaby.add(ImageIO.read(GamePanel.class.getResource("/images/head8_right.png")));
			listbaby.add(ImageIO.read(GamePanel.class.getResource("/images/head8_down.png")));
			listbaby.add(ImageIO.read(GamePanel.class.getResource("/images/head8_left.png")));
			listbaby.add(ImageIO.read(GamePanel.class.getResource("/images/body8.png")));
			System.out.println(listbaby);
			
			List<BufferedImage> listGreen = new ArrayList<>();
			listGreen.add(ImageIO.read(GamePanel.class.getResource("/images/head10_up.png")));
            listGreen.add(ImageIO.read(GamePanel.class.getResource("/images/head10_right.png")));
            listGreen.add(ImageIO.read(GamePanel.class.getResource("/images/head10_down.png")));
            listGreen.add(ImageIO.read(GamePanel.class.getResource("/images/head10_left.png")));
            listGreen.add(ImageIO.read(GamePanel.class.getResource("/images/body10.png")));


            //背景+食物+金币+角色背景（图）
			List<BufferedImage> other = new ArrayList<BufferedImage>();
			other.add(ImageIO.read(GamePanel.class.getResource("/images/Sbackground.jpg")));//开始背景图
			other.add(ImageIO.read(GamePanel.class
					.getResource("/images/backgroundB.png")));//背景图
			other.add(ImageIO.read(GamePanel.class
					.getResource("/images/SRbackground1.jpg")));//选择角色背景
			other.add(ImageIO.read(GamePanel.class
					.getResource("/images/food.png")));//蛇食物
			other.add(ImageIO.read(GamePanel.class
					.getResource("/images/food.png")));//背景闪烁食物
			other.add(ImageIO.read(GamePanel.class
					.getResource("/images/T.png")));//金币
			System.out.println(other);

			//map集合
			map.put(1,listhat);//"帽子蛇"
			map.put(2,listfrog);//"青蛙蛇"
			map.put(3,listblue);//"蓝头蛇"
			map.put(4,listblack);//"黑头蛇"
			map.put(5,list4eyes);//"4眼蛇"
			map.put(6,listred);//"红色蛇"
			map.put(7,listyellow);//"黄色蛇"
			map.put(8,listbaby);//"娃娃蛇"
			map.put(9, other);//"其他"
            map.put(10,listGreen);//绿蛇
			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final int backgroundwidth = map.get(9).get(1).getWidth();
	public static final int backgroundhight = map.get(9).get(1).getHeight();
	public static final int CELL_SIZE = 39;// 代表每一个格子的长度
	public static final int ROW = 25;// 行
	public static final int COL = 17;// 列
	public static int score;// 分数
	public static int tim;// 计时
	public static int life = 3;
//	private Balls Balla;
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
	public static Map<Long,String> nameIdMap;
	private int eatBallsNum;
	public static LinkedBlockingDeque<String> noticeQueue;
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
	public static String name;

	/**
	 * 自己的蛇
	 */
	public static Snake snake;// 蛇1

	/**
	 * 服务器IP地址
	 */
	public static String serverHost;

    /**
     * 接收到的服务器公告
     */
	public static Map<Long,String> notice;

	/**
	 * 接收到的小球
	 */
	public static Set<Ball> balls;

	public static ArrayList <Ball> oldBalls;


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
		balls = Collections.synchronizedSet(new HashSet<>());
        oldBalls = new ArrayList<>();
		eatBallsNum = 0;
		foodObjects = new HashSet<FoodObject>();
		snakes = new HashMap<>();
		nameIdMap = new HashMap<>();
		noticeQueue = new LinkedBlockingDeque<>();
		status = RUNNING;
		if (isServer){
			new Thread(() -> {
                server = Server.getInstance();
                server.start();
            }).start();

		}
		snakes = new HashMap<>();
		notice = new HashMap<>();
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
//				System.out.println(nameIdMap);
//				System.out.println(snakes);
//				System.out.println(name);
//                System.out.println(balls);
//                System.out.println("oldBalls"+oldBalls);
                System.out.println(eatBallsNum);
                try {
					Thread.sleep(200);
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
//				ballaMove();
//                removeBall();
				mutiKillMusic();
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
	 * 蛇的移动
	 */
	public void moveStep() {
		snake.move();
	}
	
	public void mutiKillMusic(){
		if(!noticeQueue.isEmpty()){
			String message = noticeQueue.poll();
			if("双杀".equals(message)){
				Gamemusic.palymusic(Gamemusic.doublekill);
			}else if("三杀".equals(message)){
				Gamemusic.palymusic(Gamemusic.triblekill);
			}else if("四杀".equals(message)){
				Gamemusic.palymusic(Gamemusic.quadrkill);
			}else if("五杀".equals(message)){
				Gamemusic.palymusic(Gamemusic.pentakill);
			}
		}
	}

	/**
	 * 统一调度的动作
	 */
	public void action() {
		KeyListen();// 方向移动
		crash();//启动碰撞监听
		moveStep();//蛇的移动
		sendSnake();//移动状态发送给服务器
//        noticeTimeout();//公告超时检测

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

        snakeCrashOtherSnake(head);
		snakeCrashWall(head);
		snakeEat(head);
		snakeEatBalls(head);
	}

	/**
	 * 头和其他蛇的头和身体的碰撞判定
	 * @param head 本机蛇头
	 */
	private void snakeCrashOtherSnake(Head head){
		for (long pid
				:snakes.keySet()){
			Snake allSnake = snakes.get(pid);
			for (Joint joint
					:allSnake.length) {
				if (CrashObjects.SnakeBang(head,joint) && pid != id){
				    if (head.getType() == 10){
				        continue;
                    }
					decLife(pid);
				}
			}
		}
	}

    /**
     * 所有蛇的头和自己的蛇相撞,并发送给服务器
     */
	/*private void snakeBodyCrashByOtherSnakes(){
        for (int i = 1; i < snake.length.size(); i++) {
           Body body = (Body) snake.length.get(i);
           for (Long id:
                   snakes.keySet()){
               Snake allSnake = snakes.get(id);
               Head allhead =(Head)allSnake.length;
               if (CrashObjects.SnakeBang(allhead,body)){
                   ClientUtil.sendSnakeData(id,allSnake,SnakeData.OPERATION_DEL_SNAKE);
               }
           }
        }
    }*/

//    private void removeBall(){
//        if (!removeBallQueue.isEmpty()){
//            synchronized (balls) {
//                balls.remove(removeBallQueue.poll());
//            }
//        }
//    }
	private void snakeEat(Head head){
		int i = 0;
		for (FoodObject food:
				foodObjects){
			if (CrashObjects.SnakeBang(head,food)){
				Gamemusic.palymusic(Gamemusic.foodmuisc);
//				System.out.println("eat food");
				if (food instanceof Food) {
					score+=10;
                    addBody();
                    
                }
                if(food instanceof Award){
					score+=20;
				    Award a = (Award)food;
				    int type = a.getAward();
				    switch (type){
                        case Award.ADD_LIFE:
                            life++;
                            break;
                        case Award.SNAKE_GOD:
                            snakeGod();
                            break;
                        case Award.SNAKE_MIN:
                            shortBody();
                            break;
                        case Award.BALL:
                            System.out.println("出现小球");
//                            new Timer().schedule(new TimerTask() {
//                                @Override
//                                public void run() {
//                                    Iterator<Ball> it = oldBalls.iterator();
//                                    int i = -1;
//                                    while(it.hasNext()){
//                                        Ball b = it.next();
//                                        synchronized (balls) {
//                                            if (i <= SnakeManager.BALLSNUM) {
//                                                balls.remove(b);
//                                                it.remove();
//                                            }
////                                            System.out.println("i = " + i);
//                                            i++;
//                                        }
//
//                                    }
//                                }
//                            },8300);
                            break;
                    }

                }
                ClientUtil.sendFoodObject(i, food, FoodObjectData.OPERATION_DEL_FOOD);
            }
		}
	}

	private void snakeEatBalls(Head head){
		if (!balls.isEmpty()) {
			
		    synchronized (balls) {
                for (Ball b :
                        balls) {
                    if (CrashObjects.SnakeBang(head, b)) {
                        score+=5;
                    	Gamemusic.palymusic(Gamemusic.foodmuisc);
                        eatBallsNum++;
                        ClientUtil.sendDelBallData(b);
                        if (eatBallsNum % 5 ==0){
                            addBody();
                        }
                    }
                }
            }
		}
	}

	/**
	 * 蛇撞到墙
	 * @param head 本机蛇头
	 */
	private void snakeCrashWall(Head head){
		if (CrashObjects.qiang(head)) {
//			decLife("哈哈，小垃圾");
//            status = DEAD;
            decLife(-1l);
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
//		if (life == 0) {
//            threadPool.execute(r4);
//            status = GAME_OVER;
			new GameFrameson().GameOver(life);
//		}
	}


	/**
	 * 增加身体长度方法
	 */
	public void addBody() {// 身体吃食物++
		// 尾部坐标
		int tailX = snake.length.get(snake.length.size() - 1).x;
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
        ClientUtil.sendSnakeData(id, snake, SnakeData.OPERATION_REL_SNAKE);
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
		eatBallsNum = 0;
		status = RUNNING;
	}

    /**
     * 无敌模式
     */
	private void snakeGod(){
	    for (Joint j
                :snake.length){
	        j.setType(10);
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                for (Joint j :
                        snake.length){
                    j.setType(xuanze.snak);
                }
            }
        },5000);
    }

    /**
     * 减一半方法
     */
    private void shortBody(){
	    int m = snake.length.size();
	    int k = m/2;
	    for (int i = (m-1);i>=k;i--){
	        snake.length.remove(i);
        }
    }

	/**
	 * 减血+弹窗方法
	 *
	 */
	public void decLife(Long killId) {// 撞墙提示
		life--;
		ClientUtil.sendSnakeData(id,snake,SnakeData.OPERATION_DEL_SNAKE,killId);

		if (life > 0) {
            status = DEAD;
            rebirth();
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
//		status = DEAD;
//		ClientUtil.sendSnakeData(id,snake,SnakeData.OPERATION_DEL_SNAKE);
//		rebirth();
//		System.out.println("delete snake");
//		Date date = new Date(0);
//		SimpleDateFormat sb = new SimpleDateFormat();
//		String Str2 = sb.format(date);
//		String str = s + "\n" + "游戏继续？" + life + "命";
//		int i = JOptionPane.showConfirmDialog(null, Str2 + "\n" + str, "游戏提示",
//				JOptionPane.YES_NO_OPTION);
//		if (i == 0) {
//			rebirth();
//		} else {
//			JOptionPane.showMessageDialog(null, "退出游戏", "标题",
//					JOptionPane.WARNING_MESSAGE);
//			System.exit(i);
//			return;
//		}
	}

	/**
	 * 撞死后重生方法
	 */
	public void rebirth(){
		snake = new Snake(snakeCreateRange(),snakeCreateRange());
		score = 0;
		status = RUNNING;
	}

	/**
	 * 发送蛇给服务器
	 */
	public void sendSnake(){
//		System.out.println("客户端发送的snake:"+ snake);
		if (status == RUNNING) {
			ClientUtil.sendSnake(snake);
//           Server.sendSnakes();
		}
//		if (status == DEAD){
//        	ClientUtil.sendSnakeData(id,snake,SnakeData.OPERATION_DEL_SNAKE);
//		}
	}

	public int snakeCreateRange(){
		return (int)((Math.random()*10)+4);
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
//		    int i = -1;
			for (int i=snake.length.size()-1;i>=0;i--) {
			   /* i++;
			    if (i == 0) {
			        switch (j.snakeDir){
                        case DOWN:
                            g.drawImage(map.get(j.getType()).get(Head.DOWN), j.getX * CELL_SIZE, j.y * CELL_SIZE, null);
                            break;
                        case LIFT:
                            g.drawImage(map.get(j.getType()).get(Head.LEFT), j.getX * CELL_SIZE, j.y * CELL_SIZE, null);
                            break;
                        case RIGHT:
                            g.drawImage(map.get(j.getType()).get(Head.RIGHT), j.getX * CELL_SIZE, j.y * CELL_SIZE, null);
                            break;
                        case UP:
                            g.drawImage(map.get(j.getType()).get(Head.UP), j.getX * CELL_SIZE, j.y * CELL_SIZE, null);

                    }
                    continue;
                }*/
//                g.drawImage(map.get(j.getType()).get(4),j.getX()*CELL_SIZE,j.getY()*CELL_SIZE,null);
				Joint j = snake.length.get(i);
                g.drawImage(j.image,j.getX()*CELL_SIZE,j.getY()*CELL_SIZE,null);
			}
		}
	}

	public void paintFoodObject(Graphics g) {
		for (FoodObject f : foodObjects) {
			g.drawImage(f.getImage(), f.getX() * CELL_SIZE, f.getY() * CELL_SIZE, null);
		}
	}

	private void paintBall(Graphics g) {
	    synchronized (balls) {
            if (balls != null) {
                for (Ball b :
                        balls) {
                    b.Draw(g);
                }
            }
        }
	}

	private void paintName(Graphics g){
		for (Long nameid:
				nameIdMap.keySet()){
			for (Long snakeid:
					snakes.keySet()){
				if (snakeid.equals(nameid)){
					Joint snakeHead = snakes.get(snakeid).length.get(0);
					g.drawString(nameIdMap.get(nameid),snakeHead.getX()*CELL_SIZE,snakeHead.getY()*CELL_SIZE-10);
//					System.out.println(nameIdMap.get(nameid));
				}
			}
		}
	}

	private void paintNotice(Graphics g){
	    if (notice != null) {
	        g.setColor(Color.RED);
	        g.setFont(new Font("黑体",Font.BOLD,50));
	        int i = 0;
            for (long id:
                    notice.keySet()){
                String message = notice.get(id);
                String name = nameIdMap.get(id);
              
                	
           
                g.drawString(name+" "+message,200,300+(i*50));
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        notice.remove(id);
                    }
                },5000);
                i++;
            }
        }
    }

//    private void noticeTimeout(){
//        Iterator<Long> it = notice.keySet().iterator();
//        while (it.hasNext()){
//            Long time = it.next();
//            if (System.currentTimeMillis() - time > 5000){
//                it.remove();
//            }
//        }
//    }

	// 画背景图,时间按,分数
	public void paint(Graphics g) {
		g.drawImage(map.get(9).get(1), 0, 0, null);// 画背景图
		g.setColor(Color.black);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.drawString("时间:" + tim, 10, 20);// 画时间
		g.drawString("分数:" + score, 10, 50);// 画分数
		g.drawString("命：" + life, 10, 80);
		paintSnake(g);// 蛇
		paintFoodObject(g);
		paintName(g);
		paintBall(g);
//		g.setColor(Color.white);
        paintNotice(g);
	}
}
