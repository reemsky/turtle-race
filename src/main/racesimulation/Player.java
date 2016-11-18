package racesimulation;


public class Player implements Runnable{
	private static boolean gameOver;
	private int position = 1;
	private String name;
	private GameField game;
	public NextPositioncalculator calculator;
	
	public Player(String name, GameField game, NextPositioncalculator calc){
		this.name = name;
		this.game = game;
		this.calculator = calc;
		gameOver = false;
	}

	
	@Override
	public void run() {
		int offset;
		try {
			game.log(name + " position is " + position);
			game.race.prepare(this);
			while (position < 50){
				if (gameOver){
					break;
				}
				game.race.getMove(this);
				offset = getNextOffset();
				position += offset;
				if (position < 1){
					position = 1;
				}
				
				game.log(name + " position = " + position + "; The move result: " + offset);
				game.updatePositions(this);
			}
			if (game.race.finish() == 1){ 
				gameOver = true;
				game.log(name + " wins!");
			}
			else {
				game.log(name + " is second!");
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String toString(){
		return name;
	}
	
	public int getNextOffset(){
		return calculator.getNextposition();
	}
	
	public int getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}
}
