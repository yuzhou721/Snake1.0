package game.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * 服务端连接事务处理器
 * Created by yuzhou721 on 2017/3/17.
 */
public class ServerHandler extends IoHandlerAdapter{
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("服务器创建连接");
        new ServerMessageHandler(session,ServerMessageHandler.CREATED);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("服务器打开连接");
       new ServerMessageHandler(session,ServerMessageHandler.OPENED);

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("客户端关闭连接");
        new ServerMessageHandler(session,ServerMessageHandler.CLOSED);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("服务器收到数据");
        new ServerMessageHandler(message,session,ServerMessageHandler.RECEIVED);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.out.println("异常断开连接");
    }



}
