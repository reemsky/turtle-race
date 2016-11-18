package racesimulation;

import java.util.Random;

public class TurtlePositionCalculator implements NextPositioncalculator{

	public int getNextposition() {
		float random = new Random().nextFloat();
		if (random <= 0.5){
			return 2;
		}
		if (random <= 0.7){
			return -4;
		}
		return 1;
	}
}
