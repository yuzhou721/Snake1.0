package game;

public class Db {
	private int head; //�Ѷ�   
	private int life ;    //��
	private int newgame ;   //���¿�ʼ״ֵ̬
	private int snake;//ѡ����
	
	Db(){
		this.head = GameFrame.head;//�Ѷ�
		this.life = GamePanel.life;//��
		this.newgame = GameFrame.number;//���¿�ʼ
		this.snake = xuanze.snak;//ѡ���ɫ
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getNumber() {
		return newgame;
	}

	public void setNumber(int newgame) {
		this.newgame = newgame;
	}



	public int getSnake() {
		return snake;
	}

	public void setSnake(int snake) {
		this.snake = snake;
	}
	

}
