package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * ���ࣺ
 * 
 * @author soft01
 *
 */
public class Snake {
    List<Joint> length;

    public Snake(){
        this(10,0);
    }
    public Snake(int x , int y){
        length = new LinkedList<>();
        length.add(new Head(x,y));
        length.add(new Body(x-1,y));
        length.add(new Body(x-2,y));
        length.add(new Body(x-3,y));
        System.out.println(length);
    }



        /**
         * ���ݷ����Զ���ǰ�ƶ�λ��
         * LIFT,RIGHT,UP,DOWN
         */
         public void move(){
             System.out.println("move");
             Direction temp = null;
             length.get(0).move();
             for (int i = length.size()-1; i > 0; i--) {
                 Joint j = length.get(i);
                 j.move();
                 temp = length.get(0).snakeDir;//��¼��һ������ķ���
                 if (i == 1) {
                     length.get(1).snakeDir = temp;
                 }
                 length.get(i).snakeDir = length.get(i-1).snakeDir;//��ǰһ������ķ���ֵ��һ������
             }

		}

        public void moveDown() {
            length.get(0).moveDown();
        }

        public void moveRight() {

            length.get(0).moveRight();

        }



        public void moveUp(){
            length.get(0).moveUp();


        }
        public void moveLeft() {
            length.get(0).moveLeft();


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
//    	snakeDir = d;
//    }
//	public dir getDir(){
//		return snakeDir;
//	}

