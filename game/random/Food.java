package game.random;

import game.GamePanel;
public class Food extends FoodObject{

	public Food(){
		this.image=GamePanel.map.get(9).get(3);
		this.mode = FoodObject.MODE_FOOD;

	}

	public Food(int x , int y){
		super(x,y);

		this.image = GamePanel.map.get(9).get(3);
		this.mode = FoodObject.MODE_FOOD;

	}
}
