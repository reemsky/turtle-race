package lv.glusakovs.racesimulation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GameField extends JFrame implements ActionListener{
	
	private JTextArea textField;
	private JScrollPane jp;
	public Race race = new Race(300);
	private AnimationPane turtle;
	private AnimationPane hare;
	
	public GameField(){
		super();
		
		setTitle("Race game");
		JPanel gameField = new JPanel();
		
		JButton btnStart = new JButton("Start");
		
		textField = new JTextArea("Click Start button to start new race.");
		textField.setEditable(false);
		textField.setRows(5);
		jp = new JScrollPane(textField);
		
		turtle = new AnimationPane("turtle");
		hare = new AnimationPane("hare");
		gameField.setLayout(new BorderLayout());
		gameField.add(hare, BorderLayout.NORTH);
		gameField.add(turtle, BorderLayout.SOUTH);
		setLayout(new BorderLayout());
		setSize(700, 320);
		add(gameField, BorderLayout.CENTER);
		
		add(jp, BorderLayout.SOUTH);
		add(btnStart, BorderLayout.EAST);
		btnStart.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (race.getRaceStatus() ){
			log ("race in progress. Please wait");
			return;
		}
		
		log("button start clicked. Race is about ot begin.");
		Thread hare, turtle;
		
		hare 	= new Thread(new Player("Hare", this::log, this::updatePositions, this.race, new HarePositionCalculator()));
		turtle 	= new Thread(new Player("Turtle", this::log, this::updatePositions, this.race, new TurtlePositionCalculator()));
		
		hare.start();
		turtle.start();
		
		log("just 1 sec before race start");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		race.startRace();
	}
	
	public void log (String msg){
		textField.insert(msg + "\n", 0);
	}

	public void updatePositions(Player player) {
		AnimationPane currentplayer = turtle;
		
		if (player.getName().equals("Hare")){
			currentplayer = hare;
		}
		
		currentplayer.updateCoord(player.getPosition());
	}
}
