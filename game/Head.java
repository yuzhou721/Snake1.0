package cn;


/**
 * ��ͷ�ࣺ
 * 
 * @author soft01
 *
 */
public class Head extends Snake {
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
    	this.image = GamePanel.head;
        this.height = this.image.getHeight();
        this.width = this.image.getWidth();
        this.x = x;
        this.y = y;
        SnakeDir = Direction.RIGHT;
    }
    
    public void move(){
    	super.move();
    }
    
    /**
     * �ж���ͷ�Ƿ�Ե�ʳ��
     * @param head ��ͷ
     * @param p ʳ��
     */
    public void eat(Head head, Food p){
        //ʳ������
        int foodX = p.x;
        int foodY = p.y;
     /*   //�ж϶���ͷ�Ƿ�Ե�ʳ��
        if(headX==foodX&&headY==foodY){//�Ե�ʳ��
        	score+=25;
        	switch(dir){
        	
        	}
        	length.add(1,new Body(x,y));
        }
        */
    }

}
