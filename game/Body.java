package game;

/**
 * �����ࣺ
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
			x--;//����
		}
		if (SnakeDir == Direction.RIGHT) {
			x++;//����
			System.out.println("����");
		}
		if (SnakeDir == Direction.UP) {
			y--;//����
		}
		if (SnakeDir == Direction.DOWN) {
			y++;//����
		}

	}


}



