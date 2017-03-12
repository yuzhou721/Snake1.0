package cn;

/**
 * 蛇身类：
 * 
 * @author soft01
 *
 */
public class Body extends Snake {
	public Body(int x, int y) {
		this.image = GamePanel.body;
		this.height = this.image.getHeight();
		this.width = this.image.getWidth();
		this.x = x;
		this.y = y;
		this.SnakeDir = Direction.RIGHT;
	}

	public void move() {
		Head h = new Head();
		h.SnakeDir = SnakeDir;
		for (int i = 0; i < length.size(); i++) {
			if (i == 0) {
				h.SnakeDir = SnakeDir;// 蛇头方向
			} else {
				switch (SnakeDir) {
				case RIGHT:
					x++;// 右移
					break;
				case LIFT:
					x--;// 左移
					break;
				case UP:
					y--;// 上移
					break;
				case DOWN:
					y++;// 下移
				}
//				dir temp = this.SnakeDir;//记录第一个蛇身的方向
//				this.SnakeDir = h.SnakeDir;//把蛇身的方向赋值下一个蛇身
//				setDir(this.SnakeDir);//改变蛇身方向
//				h.SnakeDir = temp;//把每一个蛇身当作蛇头
			}
		}
	}

}
