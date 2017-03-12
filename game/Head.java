package cn;


/**
 * 蛇头类：
 * 
 * @author soft01
 *
 */
public class Head extends Snake {
	private int score;
    //蛇头坐标
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
     * 判断蛇头是否吃到食物
     * @param head 蛇头
     * @param p 食物
     */
    public void eat(Head head, Food p){
        //食物坐标
        int foodX = p.x;
        int foodY = p.y;
     /*   //判断断蛇头是否吃到食物
        if(headX==foodX&&headY==foodY){//吃掉食物
        	score+=25;
        	switch(dir){
        	
        	}
        	length.add(1,new Body(x,y));
        }
        */
    }

}
