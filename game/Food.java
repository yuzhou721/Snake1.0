package game;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 地图上的奖励点数
 * Created by yuzhou721 on 2017/3/6.
 */
public class Food {
    int x,y;
    BufferedImage image;
    Random r = new Random();
    private static final int addLine = 0;

    public Food(){
        //todo 这个构造方法用于实现奖励的全图随机出现
        x = r.nextInt(20);
        y = r.nextInt(15);
        image = GamePanel.food;

    }

}
