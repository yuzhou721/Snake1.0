package cn;

import java.util.ArrayList;



/**
 * 蛇类：
 * 
 * @author soft01
 *
 */
public class Snake extends Joint {
    ArrayList<Snake> length;

    public Snake(){}
    public Snake(int x , int y){
        length = new ArrayList<>();
        length.add(new Head(x,y));
        length.add(new Body(x-1,y));
        length.add(new Body(x-2,y));
    }
    

    
    /**
     * 根据方向自动向前移动位置
     * LIFT,RIGHT,UP,DOWN
     */
    public void move(){
    	System.out.println("move");
//    	switch(SnakeDir){
//    	case LIFT:
//    		x--;
//    		break;
//    	case RIGHT:
//    		x++;
//    		break;
//    	case UP:
//    		y--;
//    		break;
//    	case DOWN:
//    		y++;
//    		
//    	}
    	
    	if(SnakeDir==Direction.LIFT){
    		x--;//左移
    	}
    	if(SnakeDir==Direction.RIGHT){
    		x++;//右移
    		System.out.println("向右");
    	}
    	if(SnakeDir==Direction.UP){
    		y--;//上移
    	}
    	if(SnakeDir==Direction.DOWN){
    		y++;//下移
    	}
    }

//    public ArrayList<Joint> getLength() {
//        return length;
//    }
//
//    public void setLength(ArrayList<Joint> length) {
//        this.length = length;
//    }
//    public void setDir(dir d){
//    	SnakeDir = d;
//    }
//	public dir getDir(){
//		return SnakeDir;
//	}
}
