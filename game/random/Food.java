package game.random;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import game.CrashObjects;
import game.FoodObject;
import game.GamePanel;
public class Food extends FoodObject{
	
	private static final int CELL_SIZE = 39;
	public Food(){
		this.image=GamePanel.snake_food;
		/*Random random = new Random();
		x =random.nextInt(30);
		y =random.nextInt(30);*/
		
	}
	
	/*public static BufferedImage O;
	static{
		try{
			O=ImageIO.read(Food.class.getResource("/images/snake_food.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public BufferedImage nextlmage(){
		return image;
	}
	*/

}
