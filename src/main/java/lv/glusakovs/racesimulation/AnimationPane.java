package lv.glusakovs.racesimulation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class AnimationPane extends JPanel {

	private BufferedImage icon;
	private int xPos = 1;

	public AnimationPane(String charachter) {
		try {
			icon = ImageIO.read(new File(charachter + ".png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	
	public Dimension getPreferredSize() {
		return icon == null ? super.getPreferredSize() : new Dimension(icon.getWidth() * 6, icon.getHeight());
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int y = getHeight() - icon.getHeight();
		g.drawImage(icon, xPos, y, this);
		g.drawLine(585, 0, 585, 100);

	}
	
	public void updateCoord(int x){
		xPos = x * 10;
		repaint();
	}
}

