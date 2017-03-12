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
 * ʵ�ּ���ͼƬ
 */
public class GamePanel extends JPanel {
    public static BufferedImage background; // ����ͼ
    public static BufferedImage food; // ʳ��
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

    static {
        try {
            background = ImageIO.read(GamePanel.class.getResource("/images/background1.png"));// ����ͼ
            food = ImageIO.read(GamePanel.class.getResource("/images/snake_food.png"));// ʳ��
            body = ImageIO.read(GamePanel.class.getResource("/images/body.png"));// ��ɫ����
            down = ImageIO.read(GamePanel.class.getResource("/images/down.png"));// ��ɫ��ͷ(��)
            up = ImageIO.read(GamePanel.class.getResource("/images/up.png"));// ��ɫ��ͷ(��)
            left = ImageIO.read(GamePanel.class.getResource("/images/left.png"));// ��ɫ��ͷ(��)
            right = ImageIO.read(GamePanel.class.getResource("/images/right.png"));// ��ɫ��ͷ(��)
            r_body = ImageIO.read(GamePanel.class.getResource("/images/r_body.png")); //��ɫ����
            r_down = ImageIO.read(GamePanel.class.getResource("/images/r_down.png"));// ��ɫ��ͷ(��)
            r_up = ImageIO.read(GamePanel.class.getResource("/images/r_up.png"));// ��ɫ��ͷ(��)
            r_left = ImageIO.read(GamePanel.class.getResource("/images/r_left.png"));// ��ɫ��ͷ(��)
            r_right = ImageIO.read(GamePanel.class.getResource("/images/r_right.png"));// ��ɫ��ͷ(��)

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static final int backgroundwidth = background.getWidth();
    public static final int backgroundhight = background.getHeight();
    public static final int CELL_SIZE = (background.getWidth() / 30);//����ÿһ�����ӵĳ���
    public static final int row = 30;//��
    public static final int col = 30;//��
    int fs;//����
    int tim;//��ʱ

    public Snake snake1;//��1



    /**
     * �������췽�� ��ʼ������
     */
    public GamePanel(){
        snake1 = new Snake();
    }
    //��ʱ��
    public void Timer() {
        Timer timer = new Timer();
        int index = 500;//����ʱ������һ��
        timer.schedule(new TimerTask() {
            public void run() {//��ʱ������
                tim++;
                moveStep();
                repaint();
                System.out.println("��ʱ��");
            }


        }, index, index);

    }

    public void moveStep(){
        snake1.move();
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

    //������ͼ,ʱ�䰴,����
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);//������ͼ
//        paintDq(g);
        g.setColor(new Color(0xFFFFFF));
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.drawString("ʱ��:" + tim, 10, 20);//��ʱ��
        g.drawString("����:" + fs, 10, 50);//������
        paintSnake(g);
    }
}
