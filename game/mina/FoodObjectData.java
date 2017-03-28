package game.mina;

import game.random.FoodObject;
import game.random.Food;
import game.random.Money;

/**
 * 用来传送和操做food类
 * Created by yuzhou721 on 2017/3/21.
 */
public class FoodObjectData {
    public final static short OPERATION_ADD_FOOD = 1 ; //增加
    public final static short OPERATION_DEL_FOOD = 2 ; //减少
    private FoodObject object;
//    private int mode;
    private int index;
    private short operation;

    public FoodObjectData(){}

    public FoodObjectData(FoodObject object, int index, short operation) {
        this.operation = operation;
        this.index = index;
        if (object instanceof Food){
//            mode = FoodObject.MODE_FOOD;
            this.object = object;
        }
        if (object instanceof Money){
//            mode = FoodObject.MODE_MONEY;
            this.object = object;
        }
    }



    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public short getOperation() {
        return operation;
    }

    public void setOperation(short operation) {
        this.operation = operation;
    }

    public FoodObject getObject() {
        return object;
    }

    public void setObject(FoodObject object) {
        this.object = object;
    }



}
