package game;

import java.awt.image.BufferedImage;


public abstract class Joint {
	public int getX() {
		return getX;
	}

	public void setX(int x) {
		this.getX = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	protected int getX;
	protected int y;
//	public int width;
//	public int height;
	public BufferedImage image;

    protected Direction snakeDir = Direction.RIGHT;//���˶�����


    public Joint(){}

    public Joint(int x, int y, BufferedImage image) {
        this.getX = x;
        this.y = y;
        this.image = image;
//        this. width = image.getWidth();
//        this. height = image.getHeight();
    }

    public abstract void move();

    public abstract void moveRight();
    public abstract void moveLeft();
    public abstract void moveUp();
    public abstract void moveDown();
    public void setSnakeDir(Direction snakeDir) {
        this.snakeDir = snakeDir;
    }
    
    @Override
    public String toString() {
        return "Joint{" +
                "getX=" + getX +
                ", y=" + y +
                '}';
    }

    public Direction getSnakeDir() {
        return snakeDir;
    }


}
