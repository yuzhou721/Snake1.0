package game.random;

import java.util.Random;

import game.GamePanel;

public class Money extends FoodObject implements Award {
	private int type;//奖励类型
	public Money() {
		this.image = GamePanel.map.get(9).get(4);
		this.mode = FoodObject.MODE_MONEY;
		Random r = new Random();
		int i = r.nextInt(100);
		if(i>90){
			this.type = Award.SNAKE_MIN;
		}else if(i>80){
			this.type = Award.SNAKE_GOD;
		}else if(i>50){
			
			this.type = Award.BALL;
		}else{
			this.type = Award.ADD_LIFE;
		}
		
//		this.type = Award.BALL;
	}

	public Money(int x, int y , int type) {
		super(x,y);
		image = GamePanel.map.get(9).get(5);
		this.mode = FoodObject.MODE_MONEY;
        this.type = type;
	}

	public int getAward () {
		return type;
	}


}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


