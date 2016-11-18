package lv.glusakovs.racesimulation;

import java.util.function.Consumer;

public class Player implements Runnable{
	private static boolean gameOver;
	private int position = 1;
	private String name;
	public NextPositioncalculator calculator;
	private Consumer<String> logConsumer = null;
	private Consumer<Player> positionUpdater = null;
	private Race race = null;
	
	public Player(String name, Consumer<String> logger, Consumer<Player> positioner, Race race, NextPositioncalculator calc){
		this.name = name;
		this.calculator = calc;
		this.logConsumer = logger;
		this.positionUpdater = positioner;
		this.race = race;
		gameOver = false;
	}
	
	@Override
	public void run() {
		int offset;
		try {
			logConsumer.accept(name + " position is " + position);
			race.prepare(this);
			while (position < 50){
				if (gameOver){
					break;
				}
				race.getMove(this);
				offset = getNextOffset();
				position += offset;
				if (position < 1){
					position = 1;
				}
				
				logConsumer.accept(name + " position = " + position + "; The move result: " + offset);
				positionUpdater.accept(this);
				
			}
			if (race.finish() == 1){ 
				gameOver = true;
				logConsumer.accept(name + " wins!");
			}
			else {
				logConsumer.accept(name + " is second!");
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
