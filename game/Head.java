package game;


/**
 * ��ͷ�ࣺ
 * 
 * @author soft01
 *
 */
public class Head extends Joint {
	private int score;
    //��ͷ����
    /* int headX =this.x;
    int headY = this.y;
*/
	public Head(){
		this(0,0);
	}
	
    public Head(int x, int y){
    	this.score = 0;
    	this.image = GamePanel.r_right;
        this.height = this.image.getHeight();
        this.width = this.image.getWidth();
        this.x = x;
        this.y = y;
        snakeDir = Direction.RIGHT;
    }
    
    public void move(){

        if(snakeDir ==Direction.LIFT){
            x--;//����
        }
        if(snakeDir ==Direction.RIGHT){
            x++;//����
            System.out.println("����");
        }
        if(snakeDir ==Direction.UP){
            y--;//����
        }
        if(snakeDir ==Direction.DOWN){
            y++;//����
        }

    }

    public void moveRight(){
        snakeDir = Direction.RIGHT;
        image = GamePanel.r_right;
    }

    public void moveLeft(){
        snakeDir = Direction.LIFT;
        image = GamePanel.r_left;

    }

    public void moveUp(){
        snakeDir = Direction.UP;
        image = GamePanel.r_up;

    }

    public void moveDown(){
        snakeDir = Direction.DOWN;
        image = GamePanel.r_down;

    }
    
//    /**
//     * �ж���ͷ�Ƿ�Ե�ʳ��
//     * @param head ��ͷ
//     * @param p ʳ��
//     */
//    public void eat(Head head, Food p){
//        //ʳ������
//        int foodX = p.x;
//        int foodY = p.y;
//     /*   //�ж϶���ͷ�Ƿ�Ե�ʳ��
//        if(headX==foodX&&headY==foodY){//�Ե�ʳ��
//        	score+=25;
//        	switch(dir){
//
//        	}
//        	length.add(1,new Body(x,y));
//        }
//        */
//    }

}
