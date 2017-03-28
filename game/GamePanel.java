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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
	private Ball_JP Balla;
	public static boolean doMove = true;
//	public static final int READY = 0;//׼��״̬
	public static final int RUNNING = 1;//����״̬
//	public static final int PAUSE = 2;//��ͣ״̬
	public static final int GAME_OVER = 3;
//	public static final int ACTIVE = 0;
	public static final int DEAD = 2;//����״̬
//	public static final int REMOVE = 2;
	public static int status;// �ͻ�������״̬
	public static long id;//�ӷ������Ϸ����ID
	/**
	 * �洢���е���
	 */
	public static Map<Long,Snake> snakes = null;

	/**
	 * �洢����ʳ��
	 */
	public static ArrayList<FoodObject> foodObjects;

    /**
     * ����ͨ�ŵĿͻ���
     */
    private Client client;

    /**
     * ����
     */
    private static String name;

    /**
     * �Լ�����
     */
    public static Snake snake;// ��1


    //get set���� ---------------
	public static Map<Long, Snake> getSnakes() {
		return snakes;
	}

	public static void setSnakes(Map<Long, Snake> snakes) {
		GamePanel.snakes = snakes;
	}

	public static ArrayList<FoodObject> getFoodObjects() {
		return foodObjects;
	}

	public static void setFoodObjects(ArrayList<FoodObject> foodObjects) {
		GamePanel.foodObjects = foodObjects;
	}


//-----------------------------

	/**
	 * �������췽�� ��ʼ������
	 */
	public GamePanel() {
		snake = new Snake(snakeCreateRange(),snakeCreateRange());// һ����
		Balla = new Ball_JP();// һЩ���
		foodObjects = new ArrayList<FoodObject>();
		snakes = new HashMap<>();
		status = RUNNING;

        name = "1";
        snakes = new HashMap<>();
        client = new Client(name,"127.0.0.1",9999);
        snake = new Snake(snakeCreateRange(),snakeCreateRange());
        client.start();
        Timer();
    }



	private Runnable r1 ;//��������߳�
	private Runnable r2 ;//��ʱ�߳�
	private Runnable r3 ;//�������ػ��߳�
	private Runnable r4 ;//�������߳�
	private ExecutorService threadPool ;//�̳߳�
	// ��ʱ��
	public void Timer() {

		r1 = ()-> {
			while (status == RUNNING) {
				action();
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			threadPool.execute(r4);
			System.out.println("ֹͣ�߳�1");
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

                System.out.println("�̼߳���");
				System.out.println("����״̬��"+status);
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
		snake.move();
	}

	/**
	 * ͳһ���ȵĶ���
	 */
	public void action() {
		KeyListen();// �����ƶ�
		crash();//������ײ����
        moveStep();//�ߵ��ƶ�
        sendSnake();//�ƶ�״̬���͸�������
		
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
     * ��ײ����
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
     * ͷ�������ߵ�ͷ���������ײ�ж�
     * @param head ������ͷ
     */
	private void snakeCrashByOther(Head head){
	    for (long pid
                :snakes.keySet()){
	        Snake allSnake = snakes.get(pid);
	        for (Joint joint
                    :allSnake.length) {
                if (CrashObjects.SnakeBang(head,joint) && pid != id){
                    decLife("ײ����");
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
				i++;
            }
        }
    }

    /**
     * ��ײ��ǽ
     * @param head ������ͷ
     */
    private void snakeCrashWall(Head head){
        if (CrashObjects.qiang(head)) {
            decLife("������С����");
//            status = DEAD;
        }
    }

    /**
     * ��Ϸ����
     */
	public void gameOver() {
		status = GAME_OVER;
        gameOverFrame();
	}


    /**
     * ��ʾ��Ϸ��������
     */
    public void gameOverFrame(){
        if (life == 0) {
//            threadPool.execute(r4);
//            status = GAME_OVER;
            new GameFrameson().GameOver(life);
        }
    }


    /**
     * �������峤�ȷ���
     */
	public void addBody() {// �����ʳ��++
		// β������
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
     * ���¿�ʼ����
     */
	public void Restart(){
		snake = new Snake(snakeCreateRange(),snakeCreateRange());
		score = 0;
		tim = 0;
		life = 3;
		status = RUNNING;
	}

    /**
     * ��Ѫ+��������
     *
     */
	public void decLife(String s) {// ײǽ��ʾ
        life--;
        if (life > 0) {
            notice(s);
        }
        if (life == 0 ){
            gameOver();
        }
	}

    /**
     * �����󵯴�
     * @param s �����˵�Ļ�
     */
	private void notice(String s){
	    status = DEAD;
        Date date = new Date(0);
        SimpleDateFormat sb = new SimpleDateFormat();
        String Str2 = sb.format(date);
        String str = s + "\n" + "��Ϸ������" + life + "��";
        int i = JOptionPane.showConfirmDialog(null, Str2 + "\n" + str, "��Ϸ��ʾ",
                JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            rebirth();

        } else {
            JOptionPane.showMessageDialog(null, "�˳���Ϸ", "����",
                    JOptionPane.WARNING_MESSAGE);
            System.exit(i);
            return;
        }
    }

    /**
     * ײ������������
     */
    public void rebirth(){
	    snake = new Snake(snakeCreateRange(),snakeCreateRange());
        status = RUNNING;
    }

    /**
     * �����߸�������
     */
    public void sendSnake(){
        System.out.println("�ͻ��˷��͵�snake:"+ snake);
		ClientUtil.sendSnake(snake);
//           Server.sendSnakes();
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
