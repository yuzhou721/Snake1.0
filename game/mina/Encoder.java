package game.mina;

import game.Direction;
import game.Joint;
import game.Snake;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * 编码器
 * Created by yuzhou721 on 2017/3/17.
 */
public class Encoder implements ProtocolEncoder {
    private Charset charset;
    public Encoder(Charset charset){
        this.charset = charset;
    }


    @Override
    public synchronized void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        Snake snake;
        String name;
        CharsetEncoder ce = charset.newEncoder();
        IoBuffer buffer = null;
        int size = 4 + 2;//报文头长度
        if (o instanceof Snake){
            snake = (Snake)o;
            size += snakeGetBytes(snake);
//            System.out.println("蛇长度："+size);
            buffer= IoBuffer.allocate(size).setAutoExpand(true);
            int r = buffer.remaining();
//            System.out.println("创建的buffer长度:"+r);
            buffer.putInt(size);
            buffer.putChar('a');//如果是蛇  前面加一个a字符
            encodeSnake(snake,buffer);
            r = buffer.remaining();
//            System.out.println("剩余长度是："+r);
        }

        if(o instanceof String){
            name = (String)o;
            size += Tools.getStingByteNum(name,ce);
            buffer = IoBuffer.allocate(size).setAutoExpand(true);
            buffer.putInt(size);
            buffer.putChar('b');//如果是字符串 前面加一个b字符
            buffer.putString(name,ce);
        }

        if (o instanceof Long){
            Long id = (Long)o;
            size += Tools.getBytesNum(id);
//            System.out.println("ID长度："+size);
            buffer = IoBuffer.allocate(size).setAutoExpand(true);
            int r = buffer.remaining();
//            System.out.println("创建的buffer长度:"+r);
            buffer.putInt(size);
            buffer.putChar('c');
            buffer.putLong(id);
            r = buffer.remaining();
//            System.out.println("剩余长度是："+r);
        }

        if (o instanceof SnakeData){
            SnakeData data = (SnakeData) o;
            size += snakeGetBytes(data.getSnake());
            size += Tools.getBytesNum(data.getId());
            size += Tools.getBytesNum(data.getOperation());
            buffer = IoBuffer.allocate(size).setAutoExpand(true);
            int r = buffer.remaining();
//            System.out.println("创建的buffer长度:"+r);
//            System.out.println("ID蛇长度"+size);
            buffer.putInt(size);
            buffer.putChar('d');
            buffer.putLong(data.getId());
            buffer.putShort(data.getOperation());
            encodeSnake(data.getSnake(), buffer);
            r = buffer.remaining();
//            System.out.println("剩余长度是："+r);
        }

        if (o instanceof FoodObjectData){
            System.out.println("sendfood");
            FoodObjectData data = (FoodObjectData) o;
            size += Tools.getBytesNum(data.getObject().getMode());
            size += Tools.getBytesNum(data.getObject().getX());
            size += Tools.getBytesNum(data.getObject().getY());
            size += Tools.getBytesNum(data.getIndex());
            size += Tools.getBytesNum(data.getOperation());
            buffer = IoBuffer.allocate(size).setAutoExpand(true);
            buffer.putInt(size);
            buffer.putChar('e');
            buffer.putInt(data.getObject().getMode());
            buffer.putInt(data.getObject().getX());
            buffer.putInt(data.getObject().getY());
            buffer.putInt(data.getIndex());
            buffer.putShort(data.getOperation());
        }

        buffer.flip();
        protocolEncoderOutput.write(buffer);
    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {

    }

    public void encodeSnake(Snake snake,IoBuffer buffer){
        int length = snake.length.size();
        buffer.putInt(length);
        for (Joint j :
                snake.length) {
            buffer.putInt(j.getX());
            buffer.putInt(j.getY());
            if(j.getSnakeDir() == Direction.UP){
                buffer.putInt(1);
            }else if(j.getSnakeDir() == Direction.DOWN){
                buffer.putInt(2);
            }else if(j.getSnakeDir() == Direction.LIFT){
                buffer.putInt(3);
            }else if(j.getSnakeDir() == Direction.RIGHT){
                buffer.putInt(4);
            }
        }



    }


    /**
     * 获取蛇的字节数
     * @param snake 一个蛇对象
     * @return 蛇的总字节数
     *
     * 新增一个方向Int 用j.x代替
     */
    public int snakeGetBytes(Snake snake){
        int size = 0;//
        for (Joint j:
                snake.length){
            size += Tools.getBytesNum(j.getX());
            size += Tools.getBytesNum(j.getY());
            size += Tools.getBytesNum(j.getX());

        }
        return size;
    }

}
