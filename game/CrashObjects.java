package game;

import game.random.Ball;
import game.random.FoodObject;
import game.random.Money;

import java.util.List;

import static game.GamePanel.COL;
import static game.GamePanel.ROW;

/**
 * 鍔ㄤ綔鎺у埗绫�
 * Created by yuzhou721 on 2017/3/9.
 */
public class CrashObjects {

    /*
public static boolean crashBy(Head head,FoodObject food){//食物碰撞方法
	if(head.getX ==food.x && head.y==food.y){
		return true;
	}
	return false;
}
public static boolean crashBya(Head head,Money money){//食物碰撞方法重绘
	if(money.x==head.getX &&money.y==head.y){
		return true;
	}
	return false;
}
public static boolean State(Snake snake1){//蛇身碰撞方法
	List<Joint> all = snake1.length;
	 Joint head = snake1.length.get(0);
	  for(int i=1;i<all.size();i++){
		  if(head.getX ==all.get(i).getX && head.y==all.get(i).y){
			  return true;
		  }
	  }
	return false;
}
*/
 public static boolean qiang(Head head){
	if(head.x <0||head.x >ROW-1||head.y<0||head.y>COL-1){
		return true;
	}
	return false;
 }


	/**
	 * 头和物品碰撞
	 * @return 撞到为真，没撞到为假
	 */
	public static boolean SnakeBang(Head head, Object o){
		if (o instanceof Body){
			Joint b = (Joint) o;
			Joint head2 = (Joint)head;
			if (head2.getX() == b.getX()&&head2.getY() == b.getY()){
				return true;
			}
		}

		if (o instanceof Head){
			Joint b = (Head) o ;
			Joint head2 = (Joint)head;
			if (head2.getX() == b.getX() && head2.getY() == b.getY()){
				return true;
			}
		}

		if (o instanceof FoodObject){
			Joint head2 = (Joint) head;
			FoodObject food = (FoodObject) o;
			if (head2.getX() == food.getX() && head2.getY() == food.getY()){
				return true;
			}
		}

		if (o instanceof Ball){
			Ball b = (Ball)o;
			Joint head2 = (Joint)head;
			if (head2.getX()*GamePanel.CELL_SIZE<(b.getX()+b.getD()) &&
					b.getX()<(head2.getX()*GamePanel.CELL_SIZE+head2.image.getWidth()) &&
					head2.getY()*GamePanel.CELL_SIZE<(b.getY()+b.getD()) &&
                    b.getY()<(head2.getY()*GamePanel.CELL_SIZE+head2.image.getHeight())){
			    return true;
            }
		}
		return false;
	}


}
