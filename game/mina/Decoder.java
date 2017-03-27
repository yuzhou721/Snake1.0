package game.mina;

import game.Body;
import game.random.FoodObject;
import game.Head;
import game.Snake;
import game.random.Food;
import game.random.Money;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

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
            System.out.println("长度是："+len);
            if (len > ioBuffer.remaining()){
                ioBuffer.reset();
                System.out.println(ioBuffer.remaining()+"长度不够");
                return false;
            }else{
                char mode = ioBuffer.getChar();

                if (mode == 'a') {
                    System.out.println("解码蛇");
                    deCoderSnakes(ioBuffer,protocolDecoderOutput);

                }

                if (mode == 'b'){
                    System.out.println("解码字符串");
                    deCoderString(ioBuffer,protocolDecoderOutput,len-4-2);//减去报文头 等于字符串实际长度
                }

                if (mode == 'c'){
                    System.out.println("解码ID");
                    deCoderID(ioBuffer,protocolDecoderOutput);

                }

                if(mode == 'd'){
//                    System.out.println("解码蛇+ID");
                    deCoderSnakeData(ioBuffer,protocolDecoderOutput);

                }

                if (mode == 'e'){
                    System.out.println("解码食物");
                    deCoderFoodData(ioBuffer,protocolDecoderOutput);
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
                    snake.length.add(new Head(x, y,dir));
                    continue;
                }

        int x = ioBuffer.getInt();
        int y = ioBuffer.getInt();
        int dir = ioBuffer.getInt();
        snake.length.add(new Body(x, y,dir));
        }

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
            System.out.println("Sting解码异常");
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
        int length = ioBuffer.getInt();
        decoderSnake(ioBuffer, snake, length);
        data.setId(id);
        data.setSnake(snake);
        data.setOperation(operation);

        protocolDecoderOutput.write(data);
    }

    public void deCoderFoodData(IoBuffer ioBuffer,ProtocolDecoderOutput protocolDecoderOutput){
        FoodObjectData data = new FoodObjectData();
        Food food;
        Money money;
//        FoodObject foodObject = new FoodObject(ioBuffer.getInt(),ioBuffer.getInt());
        int mode = ioBuffer.getInt();
        if (mode == FoodObject.MODE_FOOD){
            System.out.println("是食物");
            food = new Food(ioBuffer.getInt(),ioBuffer.getInt());
            data.setObject(food);
        }
        if (mode == FoodObject.MODE_MONEY){
            System.out.println("是金币" +
                    "");
            money = new Money(ioBuffer.getInt(),ioBuffer.getInt());
            data.setObject(money);
        }
        data.setIndex(ioBuffer.getInt());
        data.setOperation(ioBuffer.getShort());
        protocolDecoderOutput.write(data);
        System.out.println("解码食物成功");

    }


}

