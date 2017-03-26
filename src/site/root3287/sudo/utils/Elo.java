package site.root3287.sudo.utils;

public class Elo {
	
	private int enemyTotal, win, loss;
	public Elo(){
		this.win = 0;
		this.loss =0;
		this.enemyTotal = 0;
	}
	public Elo(int enemyTotal, int win, int loss){
		this.win = win;
		this.loss = loss;
		this.enemyTotal = enemyTotal;
	}
	public int getELO(){
		if(win+loss < 10){
			return 0;
		}
		return (enemyTotal + 50*(win-loss))/win+loss;
	}
	public int getRank(){
		int elo = this.getELO();
		int rank = 0;
		if(elo < 10){ // New account
			return rank;
		}else if(elo >= 10 && elo <= 20){ // Silver 1
			rank = 1;
		}else if(elo >= 21 && elo <= 40){ // Silver 2
			rank = 2;
		}else if(elo >= 41 && elo <= 50){
			rank = 3;
		}
		return rank;
	}
	public static int getELO(int enemy, int win, int loss){
		return (enemy+(400*(win-loss)))/(win+loss);
	}
	public static int getRank(int elo){
		int rank = 0;
		if(elo > 233 && elo < 4000){ // Silver 1
			rank = 1;
		}else if(elo > 4001 && elo < 6000){ // Silver 2
			rank = 2;
		}
		return rank;
	}
}
