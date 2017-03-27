package game;


/**
 * ��ͷ�ࣺ
 * 
 * @author soft01
 *
 */
public class Head extends Joint {
	private int score;
    //��ͷ����
    /* int headX =this.getX;
    int headY = this.y;
*/
	public Head(){
		this(0,0);
	}
	
    public Head(int x, int y){
    	this.score = 0;
    	this.image = GamePanel.r_right;
        this.getX = x;
        this.y = y;
        snakeDir = Direction.RIGHT;
    }

    public Head(int x,int y,int snakeDir){
        this(x,y);
        if (snakeDir ==1){
            this.snakeDir = Direction.UP;
            image = GamePanel.r_up;
        }else if(snakeDir == 2){
            this.snakeDir = Direction.DOWN;
            image = GamePanel.r_down;
        }else if(snakeDir == 3){
            this.snakeDir = Direction.LIFT;
            image = GamePanel.r_left;
        }else if(snakeDir == 4){
            this.snakeDir = Direction.RIGHT;
            image = GamePanel.r_right;
        }


    }
    
    public void move(){

        if(snakeDir ==Direction.LIFT){
            getX--;//����
        }
        if(snakeDir ==Direction.RIGHT){
            getX++;//����
        }
        if(snakeDir ==Direction.UP){
            y--;//����
        }
        if(snakeDir ==Direction.DOWN){
            y++;//����
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
