package game;
import game.random.Food;
import game.random.Money;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import org.omg.CORBA.PUBLIC_MEMBER;

public class FoodObject {
	
	public int x,y;
	public int COL;
	public int ROW;
	public BufferedImage image;
	public static final int CELL_SIZE=39;
	public static final int ACTIVE=0;
	public static ArrayList<FoodObject>foodObjects;
	public ArrayList<Food>foods;
	public ArrayList<Money>moneys;
	static Random random = new Random();
	public int state;//״̬
	
	
	public FoodObject(){
		x =random.nextInt(30);
		y =random.nextInt(30);
		this.image = image;
	}
	public int getX(){
		return x;
	}
	public void setX(int x){
		this.x=x;
	}
	public int getY(){
		return y;
	}
	public void setY(int y){
		this.y=y;
	}
	public int getCOL(){
		return getCOL();
	}
	public void setCOL(){
		this.COL=COL;
	}
	public int getROW(){
		return getROW();
	}
	public void setROW(){
		this.ROW=ROW;
	}
	public BufferedImage getImage(){
		return image;
	}
	public void setImage(BufferedImage image){
		this.image=image;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
