package game.random;

import java.awt.image.BufferedImage;
import java.util.Random;

public class FoodObject {

	protected int x, y,mode;
	protected BufferedImage image;
	public static final int MODE_FOOD = 1;
	public static final int MODE_MONEY = 2;


	public FoodObject() {
		Random random = new Random();
		x = random.nextInt(30);
		y = random.nextInt(30);
	}

	public FoodObject(int x, int y) {
		this.x = x;
		this.y = y;
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

    public int getMode() {
        return mode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodObject that = (FoodObject) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public BufferedImage getImage() {
        return image;
    }
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

