package cn;

/**
 * �����ࣺ
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
				h.SnakeDir = SnakeDir;// ��ͷ����
			} else {
				switch (SnakeDir) {
				case RIGHT:
					x++;// ����
					break;
				case LIFT:
					x--;// ����
					break;
				case UP:
					y--;// ����
					break;
				case DOWN:
					y++;// ����
				}
//				dir temp = this.SnakeDir;//��¼��һ������ķ���
//				this.SnakeDir = h.SnakeDir;//������ķ���ֵ��һ������
//				setDir(this.SnakeDir);//�ı�������
//				h.SnakeDir = temp;//��ÿһ����������ͷ
			}
		}
	}

}
