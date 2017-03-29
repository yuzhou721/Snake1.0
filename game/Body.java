package game;

/**
 * 蛇身类：
 *
 * @author soft01
 */
public class Body extends Joint {

	public Body(int x, int y) {
		this.image = GamePanel.map.get(type).get(4);
		this.getX = x;
		this.y = y;
		this.snakeDir = Direction.RIGHT;
	}
	public Body(int x,int y,Direction snakeDir){
		this.getX = x;
		this.y = y;
		this.snakeDir = snakeDir;
		this.image = GamePanel.map.get(type).get(4);
	}

	public Body(int x,int y,int snakeDir,int type){
		this(x,y);
		if (snakeDir ==1){
			this.snakeDir = Direction.UP;
		}else if(snakeDir == 2){
			this.snakeDir = Direction.DOWN;
		}else if(snakeDir == 3){
			this.snakeDir = Direction.LIFT;
		}else if(snakeDir == 4){
			this.snakeDir = Direction.RIGHT;
		}
		this.image = GamePanel.map.get(type).get(4);
	}

//	public Body(int x , int y , int snakeDir,){
//		this(x,y,snakeDir);
//		this.type = type;
//	}

	public void move() {
		if (snakeDir == Direction.LIFT) {
			getX--;//左移
		}
		if (snakeDir == Direction.RIGHT) {
			getX++;//右移
		}
		if (snakeDir == Direction.UP) {
			y--;//上移
		}
		if (snakeDir == Direction.DOWN) {
			y++;//下移
		}

	}

	@Override
	public void moveRight() {

	}

	@Override
	public void moveLeft() {

	}

	@Override
	public void moveUp() {

	}

	@Override
	public void moveDown() {
		snakeDir = Direction.DOWN;
	}


}



