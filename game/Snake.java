package game;

import java.util.ArrayList;


/**
 * ���ࣺ
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
		Head h = (Head) length.get(0);
		for (int i = 0; i < length.size(); i++) {
				switch (length.get(i).SnakeDir) {
					case RIGHT:
                        length.get(i).x++;// ����
						break;
					case LIFT:
                        length.get(i).x--;// ����
						break;
					case UP:
                        length.get(i).y--;// ����
						break;
					case DOWN:
                        length.get(i).y++;// ����
				}
				Direction temp = length.get(0).SnakeDir;//��¼��һ������ķ���
                length.get(i+1).SnakeDir = length.get(i).SnakeDir;//������ķ���ֵ��һ������
//				setDir(this.SnakeDir);//�ı�������
				length.get(i).SnakeDir = temp;//��ÿһ����������ͷ
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

