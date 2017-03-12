package vip;

import java.util.ArrayList;
import java.util.List;

/**
 * 动作控制类
 * Created by yuzhou721 on 2017/3/9.
 */
public class CrashObjects {
private List<Snake> all = new ArrayList<>();
public static boolean crashBy(Head head,Food food){//撞食物
	if(head.x==food.x && head.y==food.y){
		return true;
	}
	return false;
}
public void State(){//撞身体
	 Snake head = all.get(0);
	  for(int i=1;i<all.size();i++){
		  if(head.x==all.get(i).x && head.y==all.get(i).y){
			  //调用死的方法
		  }
	  }
}
 public boolean qiang(Head head,int x,int y){//撞墙
	 return head.x<0 || head.x>x || head.y<0 || head.y>y;
 }

}
