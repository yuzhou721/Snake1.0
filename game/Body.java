package game;

/**
 * …ﬂ…Ì¿‡£∫
 *
 * @author soft01
 */
public class Body extends Joint {

	public Body(int x, int y) {
		this.image = GamePanel.body;
		this.height = this.image.getHeight();
		this.width = this.image.getWidth();
		this.x = x;
		this.y = y;
		this.snakeDir = Direction.RIGHT;
	}
	public Body(int x,int y,Direction snakeDir){
		this.x = x;
		this.y = y;
		this.snakeDir = snakeDir;
		this.image = GamePanel.body;
	}

	public void move() {
		if (snakeDir == Direction.LIFT) {
			x--;//◊Û“∆
		}
		if (snakeDir == Direction.RIGHT) {
			x++;//”““∆
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



