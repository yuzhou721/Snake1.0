package game.mina;

import game.*;
import game.random.Food;
import game.random.FoodObject;
import game.random.Money;
import org.apache.mina.core.session.IoSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static game.GamePanel.foodObjects;

/**
 * 蛇的各种管理：碰撞，生成，随机事件
 * Created by yuzhou721 on 2017/3/20.
 */
public class SnakeManager {
    private static Map<Long, Snake> snakeMap = null;
    private static Map<Long,IoSession> sessionMap=null;
    private static ArrayList<FoodObject> foods = null;
    private int foodNum = 0 ;//食物出现次数
    private int person = 0;
    private Thread t1;

    public SnakeManager(){
        snakeMap = new HashMap<>();
        sessionMap = new HashMap<>();
        foods = new ArrayList<>();
        Timer();

    }

    public void Timer(){
        t1 = new Thread(()->{
            while(true){
                Action();
                Strike();
//                System.out.println(sessionMap);
//                System.out.println(snakeMap);
//                System.out.println(System.currentTimeMillis());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
    }

    /**
     * 头和物品碰撞
     * @return 撞到为真，没撞到为假
     */
    public boolean SnakeBang(Head head, Object o){
        if (o instanceof Body){
            Joint b = (Joint) o;
            Joint head2 = (Joint)head;
            if (head2.getX() == b.getX()&&head2.getY() == b.getY()){
                return true;
            }
        }

        if (o instanceof FoodObject){
            Joint head2 = (Joint) head;
            FoodObject food = (FoodObject) o;
            if (head2.getX() == food.getX() && head2.getY() == food.getY()){
                return true;
            }
        }
        return false;
    }

    /**
     * 给所有客户端发送食物信息
     * @param food 食物
     * @param index 标志
     * @param operation 操作
     */
    public void sendFood(FoodObject food , int index , short operation){
        if (sessionMap.size() != 0){
            FoodObjectData data = new FoodObjectData(food,index,operation);
            for (IoSession session:
                    sessionMap.values()){
                session.write(data);
            }
        }
    }

    /**
     * 发送食物给新连接客户端
     */
    public void sendFoodToNewClient(){
        int i = 0;
        for (FoodObject food:
                foods){
            sendFood(food,i, FoodObjectData.OPERATION_ADD_FOOD);
            i++;
        }
    }
    /**
     * 各种碰撞监视
     */
    public void Action(){

        if (sessionMap.size() != 0) {
            listenSnake();
            listenFood();
            listenWall();
        }
    }

    /**
     * 根据条件触发事件
     */
    public void Strike(){
        if (!sessionMap.isEmpty()) {
            if (foodNum == 0) {
                addFood();
                person++;
            }
        }

        if (sessionMap.size() > person ){
            sendFoodToNewClient();
            person++;
        }

        if (sessionMap.size() < person){

            person--;
        }
    }

    /**
     * 添加食物，并发送给所有客户端
     */
    public void addFood(){
        FoodObject food = nextFood();
        foods.add(foodNum,food);
        sendFood(food, foodNum, FoodObjectData.OPERATION_ADD_FOOD);
        foodNum++;
    }

    /**
     * 生成食物方法
     */
    private FoodObject nextFood() {
        int num;
        num = (int)(Math.random()*10);
        if (num < 2) {
            return new Food();
        } else {
            return new Money();
        }
    }

    /**
     * 监听蛇的碰撞
     */
    public void listenSnake(){

        for(Long id
                :snakeMap.keySet()){
            Snake snake = snakeMap.get(id);
            Head head =(Head)snake.length.get(0);
            for (Long id2
                    :snakeMap.keySet()){
                for (int i = 1; i < snakeMap.get(id2).length.size(); i++) {
                    Joint body = snakeMap.get(id2).length.get(i);
                   if (SnakeBang(head,body)){
                       replaceSessions(id,new Snake(Tools.snakeCoord(),Tools.snakeCoord()));
                       break;
                   }
                }
            }

        }

    }

    /**
     * 监听头部是否吃到东西
     */
    public void listenFood() {
        for (Long id :
                snakeMap.keySet()){
            Snake snake = snakeMap.get(id);
            Joint head = snake.length.get(0);
            int i =0;
            for (FoodObject food:
                    foods){
                if (SnakeBang((Head) head,food)){
                    foods.remove(i);
                    sendFood(food,i, FoodObjectData.OPERATION_DEL_FOOD);
                    foodNum--;
                    addFood();
                    addSnake(snake);
                    replaceSessions(id,snake);
                }
                i++;
            }
        }

    }

    /**
     * 撞墙检测
     */
    public void listenWall(){
        for (Long id:
                snakeMap.keySet()){
            Head head = (Head)snakeMap.get(id).length.get(0);
            if (head.getX()>30 || head.getX()<0 || head.getY()>30 || head.getY()<0){
                replaceSessions(id , new Snake((int)(Math.random()*26+4),(int)(Math.random()*26+4)));
            }
        }
    }



    /**
     * 根据撞到的蛇 增加1个身体
     * @param snake 吃到东西的蛇
     *
     */
    public void addSnake(Snake snake){
        Joint tail = snake.length.get(snake.length.size()-1);
        if ( tail.getSnakeDir() == Direction.UP ) {
            snake.length.add(new Body(tail.getX(), tail.getY() + 1,Direction.UP));
        }
        else if(tail.getSnakeDir() == Direction.DOWN) {
            snake.length.add(new Body(tail.getX(), tail.getY() - 1,Direction.DOWN));
        }
        else if(tail.getSnakeDir() == Direction.LIFT) {
            snake.length.add(new Body(tail.getX() + 1, tail.getY(),Direction.LIFT));
        }
        else if(tail.getSnakeDir() == Direction.RIGHT) {
            snake.length.add(new Body(tail.getX() - 1, tail.getY(),Direction.RIGHT));
        }
    }


    /**
     * 用于替换客户端上的蛇
     * @param id 被撞到的蛇的id
     * @param snake 新建的蛇
     */
    public void replaceSessions(long id , Snake snake){
        snakeMap.replace(id,snake);
        SnakeData data = new SnakeData(id,snake,SnakeData.OPERATION_REL_SNAKE);
        for (IoSession session:
                sessionMap.values()){
            System.out.println("重写蛇id:"+id);
            session.write(data);
        }
    }



    //get set方法

    public static Map<Long, Snake> getSnakeMap() {
        return snakeMap;
    }

    public static void setSnakeMap(Map<Long, Snake> snakeMap) {
        SnakeManager.snakeMap = snakeMap;
    }

    public static Map<Long, IoSession> getSessionMap() {
        return sessionMap;
    }

    public static void setSessionMap(Map<Long, IoSession> sessionMap) {
        SnakeManager.sessionMap = sessionMap;
    }

    public static ArrayList<FoodObject> getFoods() {
        return foods;
    }

    public static void setFoods(ArrayList<FoodObject> foods) {
        SnakeManager.foods = foods;
    }
}