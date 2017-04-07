package game.mina;

import game.Direction;
import game.Joint;
import game.Snake;
import game.random.Award;
import game.random.Ball;
import game.random.FoodObject;
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
            if(data.getOperation() == SnakeData.OPERATION_DEL_SNAKE){
                size += Tools.getBytesNum(data.getKillId());
            }
            buffer = IoBuffer.allocate(size).setAutoExpand(true);
            int r = buffer.remaining();
//            System.out.println("创建的buffer长度:"+r);
//            System.out.println("ID蛇长度"+size);
            buffer.putInt(size);
            buffer.putChar('d');
            buffer.putLong(data.getId());
            buffer.putShort(data.getOperation());
            if (data.getOperation() == SnakeData.OPERATION_DEL_SNAKE){
                buffer.putLong(data.getKillId());
            }
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
            if (data.getObject() instanceof Award)
                size += Tools.getBytesNum(((Award) data.getObject()).getAward());
            size += Tools.getBytesNum(data.getOperation());
            buffer = IoBuffer.allocate(size).setAutoExpand(true);
            buffer.putInt(size);
            buffer.putChar('e');
            buffer.putInt(data.getObject().getMode());
            buffer.putInt(data.getObject().getX());
            buffer.putInt(data.getObject().getY());
            if (data.getObject() instanceof Award)
                buffer.putInt(((Award) data.getObject()).getAward());
            buffer.putInt(data.getIndex());
            buffer.putShort(data.getOperation());
        }

        if (o instanceof NameData){
            System.out.println("sendName");
            NameData data = (NameData) o;
            size += Tools.getBytesNum(data.getId());
            size += Tools.getStingByteNum(data.getName(),ce);
            buffer = IoBuffer.allocate(size).setAutoExpand(true);
            buffer.putInt(size);
            buffer.putChar('f');
            buffer.putLong(data.getId());
            buffer.putString(data.getName(),ce);
        }

        if (o instanceof MessageData){
            System.out.println("sendMessage");
            MessageData data = (MessageData)o;
            size += Tools.getBytesNum(data.getType());
            size += Tools.getBytesNum(data.getId());
            size += Tools.getStingByteNum(data.getMessage(),ce);
            buffer = IoBuffer.allocate(size).setAutoExpand(true);
            buffer.putInt(size);
            buffer.putChar('g');
            buffer.putShort(data.getType());
            buffer.putLong(data.getId());
            buffer.putString(data.getMessage(),ce);
        }

        if (o instanceof BallData){
//            System.out.println("sendBall");
            BallData data = (BallData)o;
            size += ballGetBytes(data.getBall());
            size += Tools.getBytesNum(data.getOperation());
            buffer = IoBuffer.allocate(size).setAutoExpand(true);
            buffer.putInt(size);
            buffer.putChar('h');
            buffer.putShort(data.getOperation());
            encoderBall(data.getBall(),buffer);

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
            buffer.putInt(j.getType());
//            System.out.println("发送蛇的type"+j.getType());
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
            size += Tools.getBytesNum(j.getType());
        }
        return size;
    }

    private void encoderBall(Ball b , IoBuffer buffer){
        buffer.putInt(b.getX());
        buffer.putInt(b.getY());
        buffer.putInt(b.getCol().getRed());
        buffer.putInt(b.getCol().getGreen());
        buffer.putInt(b.getCol().getBlue());
        buffer.putInt(b.getD());
        buffer.putInt(b.getDir());
        buffer.putInt(b.getSpeed());
        buffer.putInt(b.getI());
    }

    private int ballGetBytes(Ball b){
        int size = 0;
        size += Tools.getBytesNum(b.getX());
        size += Tools.getBytesNum(b.getY());
        size += Tools.getBytesNum(b.getCol().getRed());
        size += Tools.getBytesNum(b.getCol().getGreen());
        size += Tools.getBytesNum(b.getCol().getBlue());
        size += Tools.getBytesNum(b.getD());
        size += Tools.getBytesNum(b.getDir());
        size += Tools.getBytesNum(b.getSpeed());
        size += Tools.getBytesNum(b.getI());
        return size;
    }

}
