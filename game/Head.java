package game;


/**
 * 蛇头类：
 * 
 * @author soft01
 *
 */
public class Head extends Joint {
	private int score;
    //蛇头坐标
    /* int headX =this.x;
    int headY = this.y;
*/
	public Head(){
		this(0,0);
	}
	
    public Head(int x, int y){
    	this.score = 0;
    	this.image = GamePanel.r_right;
        this.height = this.image.getHeight();
        this.width = this.image.getWidth();
        this.x = x;
        this.y = y;
        snakeDir = Direction.RIGHT;
    }
    
    public void move(){

        if(snakeDir ==Direction.LIFT){
            x--;//左移
        }
        if(snakeDir ==Direction.RIGHT){
            x++;//右移
        }
        if(snakeDir ==Direction.UP){
            y--;//上移
        }
        if(snakeDir ==Direction.DOWN){
            y++;//下移
        }

    }

    public void moveRight(){
        snakeDir = Direction.RIGHT;
        image = GamePanel.r_right;
    }

    public void moveLeft(){
        snakeDir = Direction.LIFT;
        image = GamePanel.r_left;

    }

    public void moveUp(){
        snakeDir = Direction.UP;
        image = GamePanel.r_up;

    }

    public void moveDown(){
        snakeDir = Direction.DOWN;
        image = GamePanel.r_down;

    }
}
