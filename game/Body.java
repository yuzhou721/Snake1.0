package game;

/**
 * 蛇身类：
 *
 * @author soft01
 */
public class Body extends Joint {

	public Body(int x, int y) {
		this.image = GamePanel.map.get(type).get(4);
		this.x = x;
		this.y = y;
		this.snakeDir = Direction.RIGHT;
	}

	/**
	 * 根据蛇尾巴的方向确定新增身体的位置
	 * @param x 新增身体的x
	 * @param y 新增身体的y
	 * @param snakeDir 新增身体方向
	 */
	public Body(int x,int y,Direction snakeDir){
		this.x = x;
		this.y = y;
		this.snakeDir = snakeDir;
		this.image = GamePanel.map.get(type).get(4);
	}

	/**
	 * 传送蛇拼接构造
	 * @param x 拼接蛇X
	 * @param y 拼接蛇Y
	 * @param snakeDir 拼接蛇的方向转换为INT
	 * @param type 拼接蛇的颜色TYPE
	 */
	public Body(int x,int y,int snakeDir,int type){
		this(x,y);
		this.type = type;
		this.image = GamePanel.map.get(type).get(4);
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

//	public Body(int x , int y , int snakeDir,){
//		this(x,y,snakeDir);
//		this.type = type;
//	}

	public void move() {
		if (snakeDir == Direction.LIFT) {
			x--;//左移
		}
		if (snakeDir == Direction.RIGHT) {
			x++;//右移
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



