package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import static java.awt.event.KeyEvent.*;

/**
 * 实现加载图片
 */
public class GamePanel extends JPanel {
    public static BufferedImage background; // 背景图
    public static BufferedImage food; // 食物
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

    static {
        try {
            background = ImageIO.read(GamePanel.class.getResource("/images/background1.png"));// 背景图
            food = ImageIO.read(GamePanel.class.getResource("/images/snake_food.png"));// 食物
            body = ImageIO.read(GamePanel.class.getResource("/images/body.png"));// 绿色蛇身
            down = ImageIO.read(GamePanel.class.getResource("/images/down.png"));// 绿色蛇头(下)
            up = ImageIO.read(GamePanel.class.getResource("/images/up.png"));// 绿色蛇头(上)
            left = ImageIO.read(GamePanel.class.getResource("/images/left.png"));// 绿色蛇头(左)
            right = ImageIO.read(GamePanel.class.getResource("/images/right.png"));// 绿色蛇头(右)
            r_body = ImageIO.read(GamePanel.class.getResource("/images/r_body.png")); //红色蛇身
            r_down = ImageIO.read(GamePanel.class.getResource("/images/r_down.png"));// 红色蛇头(下)
            r_up = ImageIO.read(GamePanel.class.getResource("/images/r_up.png"));// 红色蛇头(上)
            r_left = ImageIO.read(GamePanel.class.getResource("/images/r_left.png"));// 红色蛇头(左)
            r_right = ImageIO.read(GamePanel.class.getResource("/images/r_right.png"));// 红色蛇头(右)

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static final int backgroundwidth = background.getWidth();
    public static final int backgroundhight = background.getHeight();
    public static final int CELL_SIZE = (background.getWidth() / 30);//代表每一个格子的长度
    public static final int row = 30;//行
    public static final int col = 30;//列
    int fs;//分数
    public static int tim;//计时

    public Snake snake1;//蛇1



    /**
     * 画布构造方法 初始化数据
     */
    public GamePanel(){
        snake1 = new Snake();
    }
    //计时器
    public void Timer() {
//        Timer timer = new Timer();
//        int index = 500;//定义时间间隔，一秒
//        timer.schedule(new TimerTask() {
//            public void run() {//定时做的事
//                tim++;
//                moveStep();//测试蛇移动
//                repaint();
//                System.out.println("定时器");
//            }
//
//
//        }, index, index);

        Thread t1 = new Thread(){
            public void run(){
                while(true){
                    tim++;
                    moveStep();//测试蛇移动
                    action();
                    repaint();
                    System.out.println("线程计时器");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t2 = new Thread(){
            public void run(){
                while(true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        t1.start();
//        t2.start();

    }

    /**
     *  蛇的移动
     */
    public void moveStep(){
        snake1.move();
    }


    /**
     * 统一调度的动作
     */
    public void action(){
        Listen();
    }

    /**
     * 监听器
     */
    public void Listen(){

        KeyAdapter k = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int a = e.getKeyCode();
                switch (a){
                    case VK_W:snake1.moveUp();break;
                    case VK_S:snake1.moveDown();
                        System.out.println("向下");
                        break;
                    case VK_A:snake1.moveLeft();break;
                    case VK_D:snake1.moveRight();
                }
            }
        };
        this.requestFocus();
        this.addKeyListener(k);

    }

    public void paintDq(Graphics g) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int x = i * CELL_SIZE;
                int y = j * CELL_SIZE;
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
            }
            System.out.println();
        }

    }



    public void paintSnake(Graphics g){
        for (Joint j:
                snake1.length){
            g.drawImage(j.image,j.x*CELL_SIZE,j.y*CELL_SIZE,null);
        }
    }

    //画背景图,时间按,分数
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);//画背景图
//        paintDq(g);
        g.setColor(new Color(0xFFFFFF));
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.drawString("时间:" + tim, 10, 20);//画时间
        g.drawString("分数:" + fs, 10, 50);//画分数
        paintSnake(g);
    }
}
