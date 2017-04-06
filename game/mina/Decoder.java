package game.mina;

import game.Body;
import game.random.Ball;
import game.random.FoodObject;
import game.Head;
import game.Snake;
import game.random.Food;
import game.random.Money;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.awt.*;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * 解码器
 * Created by yuzhou721 on 2017/3/17.
 */
public class Decoder extends CumulativeProtocolDecoder {

    private Charset charset;

    public Decoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

        if (ioBuffer.remaining() < 4 ){
            return false;
        }
        if (ioBuffer.remaining() > 1 ){
            ioBuffer.mark();
            int len = ioBuffer.getInt();
            System.out.println("length:"+len);
            if (len > ioBuffer.remaining()){
                ioBuffer.reset();
                System.out.println(ioBuffer.remaining()+"length is not enough");
                return false;
            }else{
                char mode = ioBuffer.getChar();

                if (mode == 'a') {
                    System.out.println("decoder snake");
                    deCoderSnakes(ioBuffer,protocolDecoderOutput);

                }

                if (mode == 'b'){
                    System.out.println("decoder string");
                    deCoderString(ioBuffer,protocolDecoderOutput,len-4-2);//减去报文头 等于字符串实际长度
                }

                if (mode == 'c'){
                    System.out.println("decoder ID");
                    deCoderID(ioBuffer,protocolDecoderOutput);

                }

                if(mode == 'd'){
                    System.out.println("decoder snakeData");
                    deCoderSnakeData(ioBuffer,protocolDecoderOutput);

                }

                if (mode == 'e'){
                    System.out.println("decoder food");
                    deCoderFoodData(ioBuffer,protocolDecoderOutput);
                }

                if(mode == 'f'){
                    System.out.println("decoder name");
                    deCoderNameData(ioBuffer,protocolDecoderOutput,len-2-4-8);//减去报头长度再减去LONG长度
                }

                if (mode == 'g'){
                    System.out.println("decoder message");
                    deCoderMessageData(ioBuffer,protocolDecoderOutput,len-2-4-8-2);//减去报头长度再减去LongID长度
                }

                if (mode == 'h'){
                    System.out.println("decoder ball");
                    deCoderBall(ioBuffer,protocolDecoderOutput);
                }
                if (ioBuffer.remaining()>0){
                    return true;
                }

            }
            return false;

        }



//        if (mode == 'e'){
//            Food food = new Food();
//            int getX = ioBuffer.getInt();
//            int y = ioBuffer.getInt();
//            food.getX = getX;
//            food.y = y;
//            protocolDecoderOutput.write(food);
//            return true;
//        }


        return false;


    }

    @Override
    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {

    }

    public void decoderSnake(IoBuffer ioBuffer, Snake snake, int length){
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                    int x = ioBuffer.getInt();
                    int y = ioBuffer.getInt();
                    int dir = ioBuffer.getInt();
                    int type = ioBuffer.getInt();
//                System.out.println("head type="+type);
                    snake.length.add(new Head(x, y,dir,type));
                    continue;
                }

        int x = ioBuffer.getInt();
        int y = ioBuffer.getInt();
        int dir = ioBuffer.getInt();
        int type = ioBuffer.getInt();
//            System.out.println("body type="+type);
            snake.length.add(new Body(x,y,dir,type));
        }
//        System.out.println("decoderSnake 完成"+snake);

    }

    public void deCoderSnakes(IoBuffer ioBuffer,ProtocolDecoderOutput protocolDecoderOutput){

            Snake snake = new Snake();
            int length = ioBuffer.getInt();
            decoderSnake(ioBuffer,snake,length);
            protocolDecoderOutput.write(snake);

    }

    public void deCoderString(IoBuffer ioBuffer,ProtocolDecoderOutput protocolDecoderOutput,int len){
        CharsetDecoder cd = charset.newDecoder();
        String name = null;
        try {
            name = ioBuffer.getString(len,cd);
        } catch (CharacterCodingException e) {
            System.out.println("Sting decoder exception");
        }
        protocolDecoderOutput.write(name);
    }

    public void deCoderID(IoBuffer ioBuffer,ProtocolDecoderOutput protocolDecoderOutput){
        Long id = ioBuffer.getLong();
        protocolDecoderOutput.write(id);
    }

    public void deCoderSnakeData(IoBuffer ioBuffer,ProtocolDecoderOutput protocolDecoderOutput){
        Snake snake = new Snake();
        SnakeData data = new SnakeData();
//        System.out.println(ioBuffer.remaining());
        long id = ioBuffer.getLong();
        short operation = ioBuffer.getShort();
        long killId = 0;
        if (operation == SnakeData.OPERATION_DEL_SNAKE){
            killId = ioBuffer.getLong();
        }
        int length = ioBuffer.getInt();
        decoderSnake(ioBuffer, snake, length);
        data.setId(id);
        data.setSnake(snake);
        data.setOperation(operation);
        if (operation == SnakeData.OPERATION_DEL_SNAKE){
            data.setKillId(killId);
        }
        protocolDecoderOutput.write(data);
    }

    public void deCoderFoodData(IoBuffer ioBuffer,ProtocolDecoderOutput protocolDecoderOutput){
        FoodObjectData data = new FoodObjectData();
        Food food;
        Money money;
//        FoodObject foodObject = new FoodObject(ioBuffer.getInt(),ioBuffer.getInt());
        int mode = ioBuffer.getInt();
//        System.out.println("mode = "+mode);
        int x = ioBuffer.getInt();
//        System.out.println("x = " + x);
        int y = ioBuffer.getInt();
//        System.out.println("y = " + y);
        if (mode == FoodObject.MODE_FOOD){
//            System.out.println("is food");
            food = new Food(x,y);
            data.setObject(food);
        }
        if (mode == FoodObject.MODE_MONEY){
//            System.out.println("is money");
            int type = ioBuffer.getInt();
            money = new Money(x,y,type);
            data.setObject(money);
        }
        data.setIndex(ioBuffer.getInt());
        data.setOperation(ioBuffer.getShort());
        protocolDecoderOutput.write(data);
//        System.out.println("decoder food succeed");

    }

    public void deCoderNameData(IoBuffer ioBuffer,ProtocolDecoderOutput protocolDecoderOutput,int StringLen){
        NameData data = new NameData();
        CharsetDecoder cd = charset.newDecoder();
        long id = ioBuffer.getLong();
        String name = null;
        try {
            name = ioBuffer.getString(StringLen,cd);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        data.setId(id);
        data.setName(name);
        protocolDecoderOutput.write(data);
    }

    public void deCoderMessageData(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput,int StringLen){
        CharsetDecoder cd = charset.newDecoder();
        short type = ioBuffer.getShort();
        long id = ioBuffer.getLong();
        String message = null;
        try {
            message = ioBuffer.getString(StringLen,cd);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        MessageData data = new MessageData(message,id,type);
        protocolDecoderOutput.write(data);
    }

    private void deCoderBall(IoBuffer ioBuffer , ProtocolDecoderOutput protocolDecoderOutput){
        int x = ioBuffer.getInt();
        int y = ioBuffer.getInt();
        int r = ioBuffer.getInt();
        int g = ioBuffer.getInt();
        int b = ioBuffer.getInt();
        int d = ioBuffer.getInt();
        int dir = ioBuffer.getInt();
        int speed = ioBuffer.getInt();
        Color color = new Color(r,g,b);
        Ball ball = new Ball(x,y,dir,d,speed,color);
        protocolDecoderOutput.write(ball);

    }
}

