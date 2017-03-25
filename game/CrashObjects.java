package game;

import game.random.Food;
import game.random.Money;

import java.util.ArrayList;
import java.util.List;

/**
 * 鍔ㄤ綔鎺у埗绫�
 * Created by yuzhou721 on 2017/3/9.
 */
public class CrashObjects {
private static final int ROW =30;
private static final int COL =30;
public static boolean crashBy(Head head,FoodObject food){//食物碰撞方法
	if(head.x==food.x && head.y==food.y){
		return true;
	}
	return false;
}
public static boolean crashBya(Head head,Money money){//食物碰撞方法重绘
	if(money.x==head.x&&money.y==head.y){
		return true;
	}
	return false;
}
public static boolean State(Snake snake1){//蛇身碰撞方法
	List<Joint> all = snake1.length;
	 Joint head = snake1.length.get(0);
	  for(int i=1;i<all.size();i++){
		  if(head.x==all.get(i).x && head.y==all.get(i).y){
			  return true;
		  }
	  }
	return false;
}
 public static boolean qiang(Head head){
	if(head.x<0||head.x>ROW-1||head.y<0||head.y>COL-1){
		return true;
	}
	return false;
 }

}
