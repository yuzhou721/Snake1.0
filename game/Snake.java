package cn;

import java.util.ArrayList;



/**
 * ���ࣺ
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
     * ���ݷ����Զ���ǰ�ƶ�λ��
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
    		x--;//����
    	}
    	if(SnakeDir==Direction.RIGHT){
    		x++;//����
    		System.out.println("����");
    	}
    	if(SnakeDir==Direction.UP){
    		y--;//����
    	}
    	if(SnakeDir==Direction.DOWN){
    		y++;//����
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
