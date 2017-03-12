package game.random;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class Food extends JPanel{
	BufferedImage image;
	Random random = new Random();
	private static final int CELL_SIZE = 39;
	public static BufferedImage O;
	static{
		try{
			O=ImageIO.read(Food.class.getResource("/images/O.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public Food(){
		int x=100;
		int y=100;
		x =random.nextInt(20);
		y =random.nextInt(20);
		this.image=O;
	}
	public BufferedImage nextlmage(){
		return image;
	}
	
}
