package game.mina;

import game.Snake;
import org.apache.mina.core.session.IoSession;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 服务器信息处理
 * Created by yuzhou721 on 2017/3/21.
 */
public class ServerMessageHandler {
    private Object message;
    private IoSession session;
    private short operation;

    public static final short CREATED = 1;
    public static final short OPENED = 2;
    public static final short CLOSED = 3;
    public static final short RECEIVED = 4;
    public static final short EXCEPTION = 5;

    private Map<Long, IoSession> sessionMap = SnakeManager.getSessionMap();
    private Map<Long, Snake> snakeMap =SnakeManager.getSnakeMap();
    private Map<Long,String> nameIdMap = Server.getNameIdMap();




    public ServerMessageHandler(Object message,IoSession session,short operation){
        this.message = message;
        this.session = session;
        this.operation = operation;
        start();
    }

    public ServerMessageHandler(IoSession session,short operation){
        this.session = session;
        this.operation = operation;
        start();
    }

    public void start(){
        if (operation == CREATED){
            created();
        }else if(operation == OPENED){
            opened();
        }else if(operation == CLOSED){
            closed();
        }else if(operation == RECEIVED){
            received();
        }else if(operation == EXCEPTION){

        }

    }

    public void created(){
        Long id = session.getId();
        session.write(id);
        System.out.println("有客户端建立连接,ID:"+id);
    }

    private void opened(){
        long id = session.getId();
        sessionMap = SnakeManager.getSessionMap();
        sessionMap.put(id,session);
        SnakeManager.setSessionMap(sessionMap);
        System.out.println("有客户端接入");
    }

    private void closed(){
        long id = session.getId();
        System.out.println("客户端离开");
        snakeMap.remove(id);
        sessionMap.remove(id);
        nameIdMap.remove(id);
//        Server.setNameIdMap(nameIdMap);
        removeSnake(id,sessionMap.values());
//        System.out.println("删除离线客户端"+id);
    }

    private void received(){
        if (message instanceof String){
            String name =(String)message;
            storeName(name,session);
//            System.out.println(name+"名字存入成功");
        }
        if (message instanceof Snake){
            Snake snake = (Snake) message;
            storeSnake(session,snake);
//            System.out.println("收到的："+snake);
//            System.out.println("蛇存入成功");
            sendSnakes(session);
        }
    }


    /**
     * 给所有客户端发送蛇的信息 按ID编号
     * @param session 当前连接客户端的session
     */
    private void  sendSnakes(IoSession session){
        Set<Map.Entry<Long,Snake>> set = snakeMap.entrySet();
        for (Map.Entry<Long,Snake> s :
                set) {
            Long id = s.getKey();
            Snake snake = s.getValue();
            SnakeData data = new SnakeData();
            data.setSnake(snake);
            data.setId(id);
            data.setOperation(SnakeData.OPERATION_ADD_SNAKE);
            session.write(data);

        }
    }

    /**
     * 发送给所有客户端信息，删除指定离线ID的蛇
     * @param id 需要删除蛇信息的ID
     */
    public void removeSnake(Long id , Collection<IoSession> sessions){
        for (IoSession session:
                sessions){
            SnakeData data = new SnakeData(id,SnakeData.OPERATION_DEL_SNAKE);
            session.write(data);
        }
    }

    /**
     * 该方法用于设置服务器上的蛇和session对应的MAP
     * @param session 获取的当前的连接
     * @param snake 当前连接传送过来的蛇的数据
     */
    private void storeSnake(IoSession session , Snake snake){
        snakeMap = SnakeManager.getSnakeMap();
        snakeMap.put(session.getId(),snake);
        SnakeManager.setSnakeMap(snakeMap);
    }


    /**
     * 用于设置服务器上的客户端名字和session对应的Map
     * @param name 客户端名字
     * @param session 当前的连接
     */
    private  void storeName(String name , IoSession session){
        nameIdMap = Server.getNameIdMap();
        nameIdMap.put(session.getId(),name);
        Server.setNameIdMap(nameIdMap);
    }
}
