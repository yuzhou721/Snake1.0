package game;

import java.awt.image.BufferedImage;


public abstract class Joint {

    protected int x,y;
    //	public int width;
//	public int height;
    public BufferedImage image;
    protected int type;
    protected Direction snakeDir = Direction.RIGHT;//蛇运动方向

    public Joint(){
        type=xuanze.snak;
    }


    public Joint(int x, int y, BufferedImage image) {
        this.x = x;
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
                "x=" + x +
                ", y=" + y +
                ", type = "+type+
                '}';
    }

    public Direction getSnakeDir() {
        return snakeDir;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Joint joint = (Joint) o;

        if (x != joint.x) return false;
        if (y != joint.y) return false;
        if (type != joint.type) return false;
        return snakeDir == joint.snakeDir;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + type;
        result = 31 * result + (snakeDir != null ? snakeDir.hashCode() : 0);
        return result;
    }
}
