package game.random;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 
 * ???????�r?????????????
 */
public class CellRandom extends JPanel {
	private int  x,y;
	private BufferedImage image;
	private static final int CELL_SIZE = 39;
	ArrayList<CellRandom> list  ;
	Random random = new Random();
	private long nxetTime;
	public static BufferedImage T;
	Food food = new Food();
	static{
		try{
			T = ImageIO.read(CellRandom.class.getResource("/images/T.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public CellRandom(){
		x =random.nextInt(20);
		y =random.nextInt(20);
		this.image = T;
	}
	
	/**
	 * ?????
	 */
	public void nxetOne(){
		long now=System.currentTimeMillis();
		if(now>=nxetTime){
			nxetTime = now+1000;
			CellRandom cell= new CellRandom();
			
			list	= new ArrayList<>();
			list.add(cell);
			
		}
	}
	public BufferedImage nextImage(){
		return image;
	}
	public void paint(Graphics g){
		for (int i = 0; i < list.size(); i++) {
			g.drawImage(image,list.get(i).x*CELL_SIZE,list.get(i).y*CELL_SIZE,null);
			g.drawImage(food.image,x*CELL_SIZE,y*CELL_SIZE,null);
		}	
		
	}
	/**
	 * ??????
	 */
	public void action(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				nxetOne();
				
				repaint();
			}
		},0,1000/60);
	}

}
