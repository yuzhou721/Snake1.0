package game.mina;

import game.Snake;
import game.random.Ball;
import game.random.FoodObject;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;

/**
 * 客户端工具类 用于发送客户端所需的数据
 * Created by yuzhou721 on 2017/3/28.
 */
public class ClientUtil {
    private static IoSession session = Client.session;

    /**
     * 发送本机的蛇给服务器
     * @param snake 本机上的蛇类
     * @return 发送成功返回true
     */
    public static boolean sendSnake(Snake snake){

        WriteFuture future = session.write(snake);

        if (future.isWritten()){
            return true;
        }
        return false;
    }

    /**
     * 发送蛇类操作码给服务器
     * @param id 本机id
     * @param snake 被撞或者消失的snake
     * @param operation 操作码
     * @return 发送成功返回true
     */
    public static boolean sendSnakeData(long id,Snake snake,short operation){

           SnakeData data = new SnakeData(id,snake,operation);

           WriteFuture future =  session.write(data);
           if (future.isWritten()){
               return true;
           }
           return false;
    }

    public static boolean sendSnakeData(long id,Snake snake,short operation,Long killId){
        SnakeData data = new SnakeData(id,snake,operation,killId);
        WriteFuture future = session.write(data);
        if (future.isWritten()){
            return true;
        }
        return false;

    }

    /**
     * 发送操作食物方法
     * @param index 下标
     * @param food 需要操作的食物
     * @param operation 操作码
     * @return  成功返回真
     */
    public static boolean sendFoodObject(int index , FoodObject food , short operation){

        FoodObjectData data = new FoodObjectData(food,index,operation);
        WriteFuture future = session.write(data);
        if (future.isWritten()){
            return true;
        }
//        new RuntimeIoException("");
        return false;
    }

    public static boolean sendDelBallData(Ball ball){
        BallData data = new BallData(ball,BallData.BALL_DEL);
        WriteFuture future =session.write(data);
        if (future.isWritten()){
            return true;
        }
        return false;
    }
}
