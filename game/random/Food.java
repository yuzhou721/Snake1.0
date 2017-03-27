package game.random;

import game.GamePanel;
public class Food extends FoodObject{
	
	public Food(){
		this.image=GamePanel.snake_food;
		this.mode = FoodObject.MODE_FOOD;

	}

	public Food(int x , int y){
		super(x,y);

		this.image = GamePanel.snake_food;
		this.mode = FoodObject.MODE_FOOD;

	}
}
