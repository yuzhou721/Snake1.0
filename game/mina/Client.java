package game.mina;

import game.Snake;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户端
 * Created by yuzhou721 on 2017/3/17.
 */
public class Client {
    private String serverAddress = null;
    private NioSocketConnector connector = null;
    private static IoSession session = null;
    private int port;
    private String name;
    private Map<Long, Snake> snakes = null;


    public Client(String name,String serverAddress, int port){
        connector = new NioSocketConnector();
        this.port = port;
        this.serverAddress = serverAddress;
        this.name = name;
        snakes = new HashMap<>();

    }

    public void start(){
        connector.setHandler(new ClientHandler(name));
        DefaultIoFilterChainBuilder chainBuilder = connector.getFilterChain();
        connector.getFilterChain().addLast("myChin",
                new ProtocolCodecFilter(
                        new Factory("UTF-8")
                )
        );
        connector.setConnectTimeoutMillis(1000);
        connector.addListener(new IoServiceListener() {
            @Override
            public void sessionClosed(IoSession ioSession) throws Exception {

            }

            @Override
            public void serviceActivated(IoService ioService) throws Exception {

            }

            @Override
            public void serviceIdle(IoService ioService, IdleStatus idleStatus) throws Exception {

            }

            @Override
            public void serviceDeactivated(IoService ioService) throws Exception {

            }

            @Override
            public void sessionCreated(IoSession ioSession) throws Exception {

            }

            @Override
            public void sessionDestroyed(IoSession ioSession) throws Exception {
                ioSession.closeNow();
                try{
                    while(true){
                        Thread.sleep(5000);
                        ConnectFuture future = connector.connect();
                        future.awaitUninterruptibly();
                        session = future.getSession();
                        if(session !=null && session.isConnected()){
                            System.out.println("断线重连成功");
                            break;
                        }else{
                            System.out.println("断线重连失败");
                        }

                    }
                }catch (Exception e){

                }
            }
        });
        IoFuture future = connector.connect(new InetSocketAddress(serverAddress,port));
        future.awaitUninterruptibly();
        session = future.getSession();
    }

    public void sendSnake(Snake snake) {
        session.write(snake);

    }
}
