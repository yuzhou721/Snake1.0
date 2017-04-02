package game;


/**
 * 蛇头类：
 *
 * @author soft01
 *
 */
public class Head extends Joint {
    private int score;
    public static final int UP = 0; // 上
    public static final int RIGHT = 1; // 右
    public static final int DOWN = 2; // 下
    public static final int LEFT = 3; // 左
    //蛇头坐标
    /* int headX =this.getX;
    int headY = this.y;
*/
    public Head(){
        this(0,0);
    }

    /**
     * 客户端新生成蛇的构造方法
     * @param x
     * @param y
     */
    public Head(int x, int y){
        this.score = 0;
        this.image = GamePanel.map.get(type).get(RIGHT);
        this.x = x;
        this.y = y;
        snakeDir = Direction.RIGHT;
    }

    /**
     * 传送蛇数据时的拼接方法
     * @param x 蛇头X
     * @param y 蛇头Y
     * @param snakeDir 蛇方向转换的INT
     */
    public Head(int x,int y,int snakeDir, int type){
        this(x,y);
        this.type = type;
        if (snakeDir ==1){
            this.snakeDir = Direction.UP;
            image = GamePanel.map.get(type).get(UP);
        }else if(snakeDir == 2){
            this.snakeDir = Direction.DOWN;
            image = GamePanel.map.get(type).get(DOWN);
        }else if(snakeDir == 3){
            this.snakeDir = Direction.LIFT;
            image = GamePanel.map.get(type).get(LEFT);
        }else if(snakeDir == 4){
            this.snakeDir = Direction.RIGHT;
            image = GamePanel.map.get(type).get(RIGHT);
        }
    }

//    public Head(int x , int y , int snakeDir, int type){
//        this(x,y);
//        this.type = type;
//    }

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
        image = GamePanel.map.get(type).get(RIGHT);
    }

    public void moveLeft(){
        snakeDir = Direction.LIFT;
        image = GamePanel.map.get(type).get(LEFT);

    }

    public void moveUp(){
        snakeDir = Direction.UP;
        image = GamePanel.map.get(type).get(UP);

    }

    public void moveDown(){
        snakeDir = Direction.DOWN;
        image = GamePanel.map.get(type).get(DOWN);

    }
}
