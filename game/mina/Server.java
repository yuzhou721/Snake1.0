package game.mina;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务端
 * 负责连接的建立
 * Created by yuzhou721 on 2017/3/17.
 */
public class Server {
    private int port;//端口
    private static Map<Long,String> nameIdMap = null;

    public Server(int port) {
        this.port = port;
        nameIdMap = new HashMap<>();
        SnakeManager menager1 = new SnakeManager();
    }

    public void start(){
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("log",new LoggingFilter());
        acceptor.getFilterChain().addLast("myChin",
                new ProtocolCodecFilter(
                       new Factory("UTF-8")
                )
        );
        acceptor.setHandler(new ServerHandler());



        try {
            acceptor.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            System.out.println("端口被占用");
        }

    }

    public static Map<Long, String> getNameIdMap() {
        return nameIdMap;
    }

    public static void setNameIdMap(Map<Long, String> nameIdMap) {
        Server.nameIdMap = nameIdMap;
    }

    public static void main(String[] args) {
        Server server = new Server(9999);
        server.start();
    }
}


