package game.mina;

import game.Snake;

/**
 * 数据打包传送类
 * Created by yuzhou721 on 2017/3/18.
 */
public class SnakeData {
    private long id;
    public final static short OPERATION_ADD_SNAKE = 1;
    public final static short OPERATION_DEL_SNAKE = 2;
    public final static short OPERATION_REL_SNAKE = 3;
    private Short operation;
    private Snake snake;
    private Long killId;

    public SnakeData(){

    }

    public SnakeData(long id , Snake snake , Short operation) {
        this.id = id;
        this.snake = snake;
        this.operation = operation;
    }

    public SnakeData(long id,short operation){
        this.id = id;
        this.snake = new Snake();
        this.operation = operation;
    }

    public SnakeData(long id,Snake snake , Short operation,long killId){
        this(id,snake,operation);
        this.killId = killId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Short getOperation() {
        return operation;
    }

    public void setOperation(Short operation) {
        this.operation = operation;
    }

    public Long getKillId() {
        return killId;
    }

    public void setKillId(Long killId) {
        this.killId = killId;
    }
}
