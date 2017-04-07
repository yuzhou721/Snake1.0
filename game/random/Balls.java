package game.random;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JPanel;

public class Balls {
//    public Set<Ball> balls;         //存放所有的球
//    public Balls(){
//        balls = new HashSet<>();
//        for(int i=0;i<50;i++)
//            add_ball();
//    }

    public static void addBall(HashSet<Ball> balls){ //随机数产生球的初始坐标,大小,方向，颜色等
        int x=(int) (Math.random()*700);
        int y=(int) (Math.random()*500);
        int dir=(int) (Math.random()*4);
        int d=(int) (Math.random()*30);
        int r=(int) (Math.random()*255);
        int g=(int) (Math.random()*255);
        int b=(int) (Math.random()*255);
        int sp=(int) (Math.random()*1+1);
        Color col=new Color(r,g,b);
        balls.add(new Ball(x,y,dir,d,sp,col));
    }
//    public void paint(Graphics g) {  //画球方法
//
//       super.paint(g);
//        setBackground(Color.black);                 //设置画布的背景颜色
//        for(int i=0;i<balls.size();i++){            //把所有的圆画出来
//            Ball b=balls.get(i);
//            b.Draw(g);
//        }
//    }
//    public void run_run(){
//        new Thread(){
//            public void run(){
//                while(true){
//                    for(int i=1;i<balls.size();i++){               //所有球运动一次
//                        Ball b=balls.get(i);
//                        b.move();
//                    }
//                    repaint();
//                    try {
//                        Thread.sleep(30);            //间隔
//                    } catch (InterruptedException e) {  
//                    e.printStackTrace();  
//                }  
//                }  
//            }  
//        }.start();  
//    }  

//    @Override
//    public String toString() {
//        final int maxLen = 10;
//        return "Balls [balls="+ (balls != null ? balls.subList(0,
//                Math.min(balls.size(), maxLen)) : null) + "]";
//    }
}  
