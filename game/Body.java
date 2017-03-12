package game;

/**
 * 蛇身类：
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
		this.SnakeDir = Direction.RIGHT;
	}

	public void move() {
		if (SnakeDir == Direction.LIFT) {
			x--;//左移
		}
		if (SnakeDir == Direction.RIGHT) {
			x++;//右移
			System.out.println("向右");
		}
		if (SnakeDir == Direction.UP) {
			y--;//上移
		}
		if (SnakeDir == Direction.DOWN) {
			y++;//下移
		}

	}


}



