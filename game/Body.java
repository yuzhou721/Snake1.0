package game;

/**
 * …ﬂ…Ì¿‡£∫
 *
 * @author soft01
 */
public class Body extends Joint {

	public Body(int x, int y) {
		this.image = GamePanel.body;
		this.getX = x;
		this.y = y;
		this.snakeDir = Direction.RIGHT;
	}
	public Body(int x,int y,Direction snakeDir){
		this.getX = x;
		this.y = y;
		this.snakeDir = snakeDir;
		this.image = GamePanel.body;
	}

	public Body(int x,int y,int snakeDir){
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
	}

	public void move() {
		if (snakeDir == Direction.LIFT) {
			getX--;//◊Û“∆
		}
		if (snakeDir == Direction.RIGHT) {
			getX++;//”““∆
		}
		if (snakeDir == Direction.UP) {
			y--;//…œ“∆
		}
		if (snakeDir == Direction.DOWN) {
			y++;//œ¬“∆
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



