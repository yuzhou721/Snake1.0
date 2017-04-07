package game.mina;

import org.apache.mina.core.buffer.IoBuffer;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetEncoder;

/**
 * 类编写用到的工具类
 * Created by yuzhou721 on 2017/3/21.
 */
public class Tools {
    public static int getBytesNum(Object o){
        if (o instanceof Byte){
            return 1;
        }else if(o instanceof Short){
            return 2;
        }else if(o instanceof Integer){
            return 4;
        }else if(o instanceof Long){
            return 8;
        }else if(o instanceof Float){
            return 4;
        }else if(o instanceof Double){
            return 8;
        }else if(o instanceof Character){
            return 2;
        }


        return 0;
    }


    public static int getStingByteNum(String str, CharsetEncoder ce){

        IoBuffer buffer = IoBuffer.allocate(128).setAutoExpand(true);
        try {

            buffer.putString(str, ce);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
            System.out.println("String encoder Exception");
        }
        buffer.flip();
        return buffer.remaining();
    }

    public static int snakeCoord(){
        return (int)(Math.random()*19+4);
    }
}

