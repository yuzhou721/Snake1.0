package game;

import java.awt.image.BufferedImage;


public abstract class Joint {
	protected int x,y,width,height;
    protected BufferedImage image;

    protected Direction SnakeDir = Direction.RIGHT;//蛇运动方向


    public Joint(){}

    public Joint(int x, int y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this. width = image.getWidth();
        this. height = image.getHeight();
    }

    public abstract void move();

//    public void moveRight(){
//        x++;
//    }
//
//    public void moveLeft(){
//        x--;
//    }
//
//    public void moveUp(){
//        y--;
//    }
//
//    public void moveDown(){
//        y++;
//    }


    @Override
    public String toString() {
        return "Joint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
