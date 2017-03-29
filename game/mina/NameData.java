package game.mina;

/**
 * 用于打包名字
 * Created by yuzhou721 on 2017/3/29.
 */
public class NameData {
    private String name;
    private long id;
    private short operation;
    public static final short NAME_ADD = 1;
    public static final short NAME_DEL = 2;

    public NameData(){};
    public NameData(long id ,String name ){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
