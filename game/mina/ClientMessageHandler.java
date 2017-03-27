package game.mina;

import game.random.FoodObject;
import game.GamePanel;
import game.Snake;
import org.apache.mina.core.session.IoSession;

import java.util.ArrayList;
import java.util.Map;

/**
 * 客户端信息处理
 * Created by yuzhou721 on 2017/3/21.
 */
public class ClientMessageHandler {
    private Object message;
    private IoSession session;
    private short operation;

//    private Snake snake;
    private Map<Long, Snake> snakes;

    public static final short CREATED = 1;
    public static final short OPENED = 2;
    public static final short CLOSED = 3;
    public static final short RECEIVED = 4;
    public static final short EXCEPTION = 5;


    public ClientMessageHandler(Object message, IoSession session , short operation){
        this.message = message;
        this.session = session;
        this.operation = operation;
        start();
    }

    public ClientMessageHandler(IoSession session,short operation){
        this.session = session;
        this.operation = operation;
        start();
    }

    public void start (){
        if (operation == RECEIVED){
            received();
        }else if(operation == OPENED){
            opened();
        }
    }

    private void opened(){
        sendSnake(session);

    }
    public void received(){
        Long id2 = session.getId();
        long ids;
//        System.out.println(id2+"客户端接收到数据");

        if (message instanceof Long ){
            ids = (Long)message;
            GamePanel.id = ids;
//            System.out.println(ids+"发来数据");

        }

        if (message instanceof SnakeData){
            SnakeData data =(SnakeData)message;
            long id = data.getId();
            Snake snake = data.getSnake();
            short operation = data.getOperation();
            receiveSnake(id, snake,operation);
        }

        if (message instanceof FoodObjectData){
            FoodObjectData data = (FoodObjectData) message;
            receiveFood(data.getObject(),data.getIndex(),data.getOperation());
        }
    }

    /**
     * 发送蛇的方法
     * @param session
     */
    public static void sendSnake(IoSession session) {
        Snake snake = GamePanel.snake;
        session.write(snake);
    }


    /**
     * 把蛇存到客户端的方法
     * @param id sessionID
     * @param message 发送过来的蛇的数据
     * @param operation 操作码 添加或者删除,替换
     */
    private void receiveSnake(Long id,Object message,short operation){
        Snake snake = (Snake)message;
        snakes = GamePanel.getSnakes();
        if (operation == SnakeData.OPERATION_ADD_SNAKE){
            snakes.put(id, snake);
            GamePanel.setSnakes(snakes);
        }

        if (operation == SnakeData.OPERATION_DEL_SNAKE){
            snakes.remove(id);
//            System.out.println("删除"+id+"号蛇成功");
        }

        if (operation == SnakeData.OPERATION_REL_SNAKE){
            System.out.println("用于替换的id"+id);
            System.out.println("用于替换的snake:"+snake);
               snakes.replace(id,snake);
                if (id == GamePanel.id) {
                    GamePanel.snake = snake;
                }else{
                    System.out.println("ID不一样"+id+","+GamePanel.id);
                }

        }
    }

    /**
     * 储存食物方法
     * @param food 食物
     * @param index 食物下标
     * @param operation 操作码
     */
    private void receiveFood(FoodObject food, int index, short operation){
        ArrayList<FoodObject> foods = GamePanel.foodObjects;
        if (operation == FoodObjectData.OPERATION_ADD_FOOD){
            System.out.println("收到食物");

            if (foods.size() != 0) {
                for (FoodObject food1 :
                        foods) {
                    if (food1.equals(food)) {
                        System.out.println("已经有这个食物了");
                    } else {
                        foods.add(index, food);
                        System.out.println("还没有这个食物");
                    }
                }
            }else{
                foods.add(index, food);
            }
        }
        if (operation == FoodObjectData.OPERATION_DEL_FOOD){
            foods.remove(index);
            System.out.println("吃到了食物");
        }
    }

}
