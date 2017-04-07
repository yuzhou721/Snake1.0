package game.mina;

import game.random.Ball;

/**
 * 小球打包传送类
 * Created by yuzhou721 on 2017/4/7.
 */
public class BallData {
    private Ball ball;
    private short operation;
    public static final short BALL_ADD = 1;
    public static final short BALL_DEL = 2;

    public BallData(Ball ball,Short operation){
        this.ball = ball;
        this.operation = operation;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public short getOperation() {
        return operation;
    }

    public void setOperation(short operation) {
        this.operation = operation;
    }
}
