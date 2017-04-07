package game.random;

import game.GamePanel;

public class Money extends FoodObject implements Award {
	private int type;//奖励类型
	public Money() {
		this.image = GamePanel.map.get(9).get(5);
		this.mode = FoodObject.MODE_MONEY;
//		this.type =(int) (Math.random()*3);
		this.type = Award.BALL;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	


