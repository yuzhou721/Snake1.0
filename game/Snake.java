package game;

import java.util.ArrayList;


/**
 * 蛇类：
 * 
 * @author soft01
 *
 */
public class Snake {
    ArrayList<Joint> length;

    public Snake(){
        this(10,0);
    }
    public Snake(int x , int y){
        length = new ArrayList<>();
        length.add(new Head(x,y));
        length.add(new Body(x-1,y));
        length.add(new Body(x-2,y));
        length.add(new Body(x-3,y));
    }
    

    
    /**
     * 根据方向自动向前移动位置
     * LIFT,RIGHT,UP,DOWN
     */
    public void move(){
    	System.out.println("move");
//		Head h = (Head) length.get(0);
		for (int i = 0; i < length.size(); i++) {
				/*switch (length.get(i).SnakeDir) {
					case RIGHT:
                        length.get(i).move();// 右移
						break;
					case LIFT:
                        length.get(i).move();// 左移
						break;
					case UP:
                        length.get(i).move();// 上移
						break;
					case DOWN:
                        length.get(i).move();// 下移
				}*/
				length.get(i).move();
//				Direction temp = length.get(0).SnakeDir;//记录第一个蛇身的方向
//                length.get(i+1).SnakeDir = length.get(i).SnakeDir;//把蛇身的方向赋值下一个蛇身
//				setDir(this.SnakeDir);//改变蛇身方向
//				length.get(i).SnakeDir = temp;//把每一个蛇身当作蛇头
			}
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

