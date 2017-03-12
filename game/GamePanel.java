package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

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

    public int backgroundwidth = background.getWidth();
    public int backgroundhight = background.getHeight();
    private static final int BACK_CD = (background.getWidth() / 30);//代表每一个格子的长度
    private static final int row = 30;//行
    private static final int col = 30;//列
    int fs;//分数
    int tim;//计时


    //计时器
    public void Timer() {
        Timer timer = new Timer();
        int index = 1000;//定义时间间隔，一秒
        timer.schedule(new TimerTask() {
            public void run() {//定时做的事
                tim++;

                repaint();
            }


        }, index, index);

    }


    public void paintDq(Graphics g) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int x = i * BACK_CD;
                int y = j * BACK_CD;
                g.drawRect(x, y, BACK_CD, BACK_CD);
            }
            System.out.println();
        }

    }


    //画背景图,时间按,分数
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);//画背景图
        //paintDq(g);
        g.setColor(new Color(0xFFFFFF));
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.drawString("时间:" + tim, 10, 20);//画时间
        g.drawString("分数:" + fs, 10, 50);//画分数

    }

    public static void main(String[] args) {


    }


}
