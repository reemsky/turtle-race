package racesimulation;


public class Race {
	private int place = 1;
	private boolean raceinprogress = false;
	private int delay;
	
	public Race (int delay){
		this.delay = delay;
	}
	
	public synchronized void prepare(Player player) throws InterruptedException{
		this.wait();
	}
		
	public synchronized void startRace(){
		raceinprogress = true;
		place = 1;
		this.notifyAll();
	}
	
	public synchronized void getMove(Player player){
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized int finish(){
		raceinprogress = false;
		return place++;
	}

	public boolean getRaceStatus(){
		return raceinprogress;
	}
}
