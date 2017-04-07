package game.mina;

/**
 * 文本消息发送类
 * Created by yuzhou721 on 2017/4/5.
 */
public class MessageData {
    private String message;
//    private String name;
    private Long id;
    private short type;
    public static final short TYPE_CHAT = 2;
    public static final short TYPE_NOTICE = 1;
    public static final short TYPE_OPE = 3;

    public MessageData(String message, Long id , short type){
        this.message = message;
        this.id = id;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
