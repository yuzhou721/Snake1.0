package game.random;

public interface Award {
	int ADD_LIFE = 0;//增加生命
	int SNAKE_GOD=1;//无敌
	int SNAKE_MIN=2;//变短
	int BALL=3;//出现小球
	int getAward();
}
