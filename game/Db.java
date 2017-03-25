package game;

public class Db {
	private int head; //难度   
	private int life ;    //命
	private int newgame ;   //重新开始状态值
	private int snake;//选择蛇
	
	Db(){
		this.head = GameFrame.head;//难度
		this.life = GamePanel.life;//命
		this.newgame = GameFrame.number;//重新开始
		this.snake = xuanze.snak;//选择角色
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getNumber() {
		return newgame;
	}

	public void setNumber(int newgame) {
		this.newgame = newgame;
	}



	public int getSnake() {
		return snake;
	}

	public void setSnake(int snake) {
		this.snake = snake;
	}
	

}
