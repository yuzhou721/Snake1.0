package game.mina;

import game.Snake;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.util.Map;

/**
 * 客户端事务处理器
 * Created by yuzhou721 on 2017/3/17.
 */
public class ClientHandler extends IoHandlerAdapter {
    private String name;


    public ClientHandler(String name) {
        this.name = name;
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("建立连接");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        session.write(name);
        System.out.println(name+"已经连接");
        new ClientMessageHandler(session,ClientMessageHandler.OPENED);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("断开连接");
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {

    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        new ClientMessageHandler(message,session,ClientMessageHandler.RECEIVED);

    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.out.println("连接异常断开");
    }



}
