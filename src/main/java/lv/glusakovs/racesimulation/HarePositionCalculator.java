package lv.glusakovs.racesimulation;

import java.util.Random;

public class HarePositionCalculator implements NextPositioncalculator{

	public int getNextposition() {
		float random = new Random().nextFloat();
		if (random < 0.2){
			return 0;
		}
		if (random < 0.4){
			return 7;
		}
		if (random < 0.5){
			return -10;
		}
		if (random < 0.8){
			return 1;
		}
		return -2;
	}
}
