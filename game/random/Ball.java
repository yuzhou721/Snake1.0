package game.random;

import java.awt.Color;  
import java.awt.Graphics;  
  
  
public class Ball {  
	private int x,y,dir,d;
	private int speed;
	private int i;
	private Color col;
    private static final int left_up=0,right_up=1,left_down=2,right_down=3;
    static int count = 0;

    public  Ball(int x,int y,int dir,int d,int speed,Color col){  
        super();  
        this.x=x ;  
        this.y=y;  
       this.dir=dir;  
        this.d=d;  
        this.speed=speed;  
        this.col=col;
        this.i = count;
        count++;
     
    }  
    public void Draw(Graphics g){
        g.setColor(col);  
        g.fillOval(x, y, d, d);
    }  
    public void move(){
        switch (this.dir) {  
        case left_up:  
            x-=speed;y-=speed;  
            if(x<=0)dir=right_up;  
            else if(y<=0)dir=left_down;  
            break;  
        case right_up:  
            x+=speed;y-=speed;  
            if(x>=800-d)dir=left_up;  
            else if(y<=0)dir=right_down;  
            break;  
        case left_down:  
            x-=speed;y+=speed;  
            if(x<=0)dir=right_down;  
            else if(y>=600-d)dir=left_up;  
            break;  
        case right_down:  
            x+=speed;y+=speed;  
            if(x>=800-d)dir=left_down;  
            else if(y>=600-d)dir=right_up;  
            break;  
        }  
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Color getCol() {
        return col;
    }

    public void setCol(Color col) {
        this.col = col;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
    	return "Ball [getX=" + x + ", y=" + y + ", dir=" + dir + ", d=" + d
    			+ ", speed=" + speed + ", col=" + col + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ball ball = (Ball) o;

        return i == ball.i;
    }

    @Override
    public int hashCode() {
        return i;
    }
}

